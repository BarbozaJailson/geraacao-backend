package br.com.belval.api.geraacao.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import br.com.belval.api.geraacao.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.belval.api.geraacao.dto.MovimentacaoEstoqueResponseDTO;
import br.com.belval.api.geraacao.model.Doacao;
import br.com.belval.api.geraacao.model.Estoque;
import br.com.belval.api.geraacao.model.Instituicao;
import br.com.belval.api.geraacao.model.Item;
import br.com.belval.api.geraacao.model.MovimentacaoEstoque;
import br.com.belval.api.geraacao.model.Requisicao;
import br.com.belval.api.geraacao.model.TipoMovimentacao;
import br.com.belval.api.geraacao.repository.DoacaoRepository;
import br.com.belval.api.geraacao.repository.EstoqueRepository;
import br.com.belval.api.geraacao.repository.InstituicaoRepository;
import br.com.belval.api.geraacao.repository.ItemRepository;
import br.com.belval.api.geraacao.repository.MovimentacaoEstoqueRepository;
import br.com.belval.api.geraacao.repository.RequisicaoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MovimentacaoEstoqueServiceImpl implements MovimentacaoEstoqueService{

    private final MovimentacaoEstoqueRepository movRepo;
    private final EstoqueRepository estoqueRepo;
    private final ItemRepository itemRepo;
    private final RequisicaoRepository requisicaoRepo;
    private final DoacaoRepository doacaoRepo;
    private final InstituicaoRepository instituicaoRepo;

    public MovimentacaoEstoqueServiceImpl(MovimentacaoEstoqueRepository movRepo,
                                          EstoqueRepository estoqueRepo,
                                          ItemRepository itemRepo,
                                          RequisicaoRepository requisicaoRepo,
                                          DoacaoRepository doacaoRepo,
                                          InstituicaoRepository instituicaoRepo) {
        this.movRepo = movRepo;
        this.estoqueRepo = estoqueRepo;
        this.itemRepo = itemRepo;
        this.requisicaoRepo = requisicaoRepo;
        this.doacaoRepo = doacaoRepo;
        this.instituicaoRepo = instituicaoRepo;
    }

    @Override
    @Transactional
    public MovimentacaoEstoqueResponseDTO criarMovimentacao(Integer itemId, Integer instituicaoId, Integer requisicaoId, Integer doacaoId, TipoMovimentacao tipoMovimentacao, int quantidade) {
        Item item = itemRepo.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item não encontrado: " + itemId));
        Instituicao instituicao = instituicaoRepo.findById(instituicaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Instituição não encontrada: " + instituicaoId));
        Requisicao requisicao = requisicaoRepo.findById(requisicaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Requisição não encontrada: " + requisicaoId));
        Doacao doacao = doacaoRepo.findById(doacaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Doação não encontrada: " + doacaoId));
        Estoque estoque = estoqueRepo.findByItemAndInstituicao(item, instituicao).orElse(null);
        if (estoque == null) {
             //Cria novo estoque se não existir
            if (tipoMovimentacao == TipoMovimentacao.SAIDA) {
                throw new IllegalArgumentException("Não há estoque suficiente para saída.");
            }
            estoque = new Estoque();
            estoque.setItem(item);
            estoque.setInstituicao(instituicao);
            estoque.setQuantidade(quantidade);
        } else {
            // Atualiza estoque existente
            int novaQtd = estoque.getQuantidade();
            if (tipoMovimentacao == TipoMovimentacao.ENTRADA) {
                estoque.setQuantidade(estoque.getQuantidade() + quantidade);
            } else if (tipoMovimentacao == TipoMovimentacao.SAIDA) {
                if (estoque.getQuantidade() < quantidade) {
                    throw new IllegalArgumentException("Quantidade insuficiente em estoque.");
                }
                estoque.setQuantidade(estoque.getQuantidade() - quantidade);
            }
        }
        estoqueRepo.save(estoque);
        MovimentacaoEstoque movimento = new MovimentacaoEstoque();
        movimento.setAtivo(true);
        movimento.setItem(item);
        movimento.setInstituicao(instituicao);
        movimento.setQuantidade(quantidade);
        movimento.setTipoMovimentacao(tipoMovimentacao);
        movimento.setDataMovimentacao(LocalDateTime.now());
        movimento.setRequisicao(requisicao);
        movimento.setDoacao(doacao);
        movimento.setObservacao(" Inserção automática");
        // Você pode adicionar lógica para associar requisicao/doacao aqui se quiser.
        movRepo.save(movimento);
        return new MovimentacaoEstoqueResponseDTO(movimento);
    }

    // Registo de saida de estoque
    @Override
    @Transactional
    public MovimentacaoEstoqueResponseDTO registrarSaidaEstoque(Integer idItem, Integer idInstituicao, Integer quantidade, TipoMovimentacao tipoMovimentacao, String observação) {
        // 1 - Busca o item
        Item item = itemRepo.findById(idItem)
                .orElseThrow(() -> new ResourceNotFoundException("Item não encontrado: " + idItem));
        // 2 - Busca a instituição
        Instituicao instituicao = instituicaoRepo.findById(idInstituicao)
                .orElseThrow(() -> new ResourceNotFoundException("Instituição não encontrada: " + idInstituicao));
        // 3 - Busca o estoque existente
        Estoque estoque = estoqueRepo.findByItemAndInstituicao(item, instituicao)
                .orElseThrow(() -> new ResourceNotFoundException("Estoque não encontrado para este item/instituição"));
        // 4 - Valida saldo
        if (estoque.getQuantidade() < quantidade) {
            throw new IllegalArgumentException("Quantidade solicitada é maior que o estoque disponível");
        }
        // 5 - Atualiza saldo
        estoque.setQuantidade(estoque.getQuantidade() - quantidade);
        estoqueRepo.save(estoque);
        // 6 - Registra movimento de saída
        MovimentacaoEstoque movimento = new MovimentacaoEstoque();
        movimento.setItem(item);
        movimento.setAtivo(true);
        movimento.setInstituicao(instituicao);
        movimento.setQuantidade(quantidade);
        movimento.setTipoMovimentacao(TipoMovimentacao.SAIDA); // grava como string "SAIDA"
        movimento.setDataMovimentacao(LocalDateTime.now());
        movimento.setObservacao(movimento.getObservacao());
        movRepo.save(movimento);
        return new MovimentacaoEstoqueResponseDTO(movimento);
    }

    // Busca movimentações de estoque por Instituição
    @Override
    public List<MovimentacaoEstoqueResponseDTO> BuscarPorInstituicao(Integer idInstituicao) {
        Instituicao instit = instituicaoRepo.findById(idInstituicao)
                .orElseThrow(() -> new ResourceNotFoundException("Movimentação não encontrada para instituição com id " + idInstituicao));
        List<MovimentacaoEstoque> movim = movRepo.findByInstituicao(instit);
        return movim.stream()
                .map(MovimentacaoEstoqueResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Busca movimentações de estoque por Id
    @Override
    public MovimentacaoEstoqueResponseDTO buscarPorId(Integer id) {
        MovimentacaoEstoque mov = movRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimentação não encontrada com id: " + id));
        return new MovimentacaoEstoqueResponseDTO(mov);
    }
    @Override
    public MovimentacaoEstoqueResponseDTO movimentoByCampanhaDelete(Integer itemId, Integer instituicaoId, Integer quantidade, TipoMovimentacao tipoMovimentacao, String observacao){
        Item item = itemRepo.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item não encontrado: " + itemId));
        Instituicao instituicao = instituicaoRepo.findById(instituicaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Instituição não encontrada: " + instituicaoId));
        Estoque estoque = estoqueRepo.findByItemAndInstituicao(item, instituicao).orElse(null);
        if (estoque == null) {
            //Cria novo estoque se não existir
            if (tipoMovimentacao == TipoMovimentacao.SAIDA) {
                throw new IllegalArgumentException("Não há estoque suficiente para saída.");
            }
            estoque = new Estoque();
            estoque.setItem(item);
            estoque.setInstituicao(instituicao);
            estoque.setQuantidade(quantidade);
        } else {
            // Atualiza estoque existente
            int novaQtd = estoque.getQuantidade();
            if (tipoMovimentacao == TipoMovimentacao.UPDATE) {
                estoque.setQuantidade(estoque.getQuantidade() + quantidade);
            } else if (tipoMovimentacao == TipoMovimentacao.SAIDA) {
                if (estoque.getQuantidade() < quantidade) {
                    throw new IllegalArgumentException("Quantidade insuficiente em estoque.");
                }
                estoque.setQuantidade(estoque.getQuantidade() - quantidade);
            }
        }
        estoqueRepo.save(estoque);
        MovimentacaoEstoque movimento = new MovimentacaoEstoque();
        movimento.setItem(item);
        movimento.setAtivo(true);
        movimento.setInstituicao(instituicao);
        movimento.setQuantidade(quantidade);
        movimento.setTipoMovimentacao(tipoMovimentacao);
        movimento.setDataMovimentacao(LocalDateTime.now());
        movimento.setRequisicao(null);
        movimento.setDoacao(null);
        movimento.setObservacao(observacao);
        // Você pode adicionar lógica para associar requisicao/doacao aqui se quiser.
        movRepo.save(movimento);
        return new MovimentacaoEstoqueResponseDTO(movimento);
    }

    @Override
    public MovimentacaoEstoqueResponseDTO movimentoByCampanhaUpdate(Integer itemId, Integer instituicaoId, Integer qtdCampanha, TipoMovimentacao tipoMovimentacao, String observacao, Integer novaQtd){
        Item item = itemRepo.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item não encontrado: " + itemId));
        Instituicao instituicao = instituicaoRepo.findById(instituicaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Instituição não encontrada: " + instituicaoId));
        Estoque estoque = estoqueRepo.findByItemAndInstituicao(item, instituicao).orElse(null);
        int qtdEstoque = estoque.getQuantidade();
        if((qtdEstoque + qtdCampanha) >= novaQtd) {
            int estoqueNovaQtd = (qtdEstoque + qtdCampanha) - novaQtd;
            estoque.setQuantidade(estoqueNovaQtd);
        }
        estoqueRepo.save(estoque);
        MovimentacaoEstoque movimento = new MovimentacaoEstoque();
        movimento.setItem(item);
        movimento.setInstituicao(instituicao);
        movimento.setAtivo(true);
        movimento.setQuantidade(qtdCampanha);
        movimento.setTipoMovimentacao(tipoMovimentacao);
        movimento.setDataMovimentacao(LocalDateTime.now());
        movimento.setRequisicao(null);
        movimento.setDoacao(null);
        movimento.setObservacao(observacao);
        // Você pode adicionar lógica para associar requisicao/doacao aqui se quiser.
        movRepo.save(movimento);
        return new MovimentacaoEstoqueResponseDTO(movimento);
    }

}

