package br.com.belval.api.geraacao.service;

import br.com.belval.api.geraacao.dto.CampanhaCreateDTO;
import br.com.belval.api.geraacao.dto.CampanhaResponseDTO;
import br.com.belval.api.geraacao.dto.CampanhaUpdateDTO;
import br.com.belval.api.geraacao.exception.ResourceNotFoundException;
import br.com.belval.api.geraacao.model.Campanha;
import br.com.belval.api.geraacao.model.Instituicao;
import br.com.belval.api.geraacao.model.Item;
import br.com.belval.api.geraacao.model.TipoMovimentacao;
import br.com.belval.api.geraacao.repository.CampanhaRepository;
import br.com.belval.api.geraacao.repository.InstituicaoRepository;
import br.com.belval.api.geraacao.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CampanhaServiceImpl implements CampanhaService {
    private static final Logger logger = LoggerFactory.getLogger(CampanhaServiceImpl.class);
    private final CampanhaRepository campanhaRepository;
    private final InstituicaoRepository instituicaoRepository;
    private final ItemRepository itemRepository;
    private final MovimentacaoEstoqueService movimentacaoEstoqueService;
    public CampanhaServiceImpl(CampanhaRepository campanhaRepository, InstituicaoRepository instituicaoRepository, ItemRepository itemRepository, MovimentacaoEstoqueService movimentacaoEstoqueService) {
        this.campanhaRepository = campanhaRepository;
        this.instituicaoRepository = instituicaoRepository;
        this.itemRepository = itemRepository;
        this.movimentacaoEstoqueService = movimentacaoEstoqueService;
    }
    @Transactional(readOnly = true)
    @Override
    public List<CampanhaResponseDTO> findAll() {
            List<Campanha> campanhas = campanhaRepository.findAll();
            if(campanhas.isEmpty()) {
                throw new ResourceNotFoundException("Nenhuma campanha encontrada");
            }
            return campanhas.stream()
                    .map(CampanhaResponseDTO::new)
                    .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    @Override
    public List<CampanhaResponseDTO> findByInstituicaoId(Integer instituicaoId) {
            List<Campanha> campanhas = campanhaRepository.findByInstituicaoId(instituicaoId);
            if(campanhas.isEmpty()) {
                throw new ResourceNotFoundException("Nenhuma campanha encontrada para a instituição com id: " + instituicaoId);
            }
            return campanhas.stream()
                    .map(CampanhaResponseDTO::new)
                    .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    @Override
    public CampanhaResponseDTO findById(Integer id) {
        Campanha campanha = campanhaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campanha com id " + id + " não encontrado"));
        return new CampanhaResponseDTO(campanha);
    }
    @Transactional
    @Override
    public CampanhaResponseDTO criarCampanha(CampanhaCreateDTO dto) {
        // 1 - Busca entidades relacionadas
        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new ResourceNotFoundException("Item não encontrado: " + dto.getItemId()));
        Instituicao instituicao = instituicaoRepository.findById(dto.getInstituicaoId())
                .orElseThrow(() -> new ResourceNotFoundException("Instituição não encontrada: " + dto.getInstituicaoId()));
        // 2 - Cria a campanha
        Campanha campanha = new Campanha();
        campanha.setDescricao(dto.getDescricao());
        campanha.setDataEvento(dto.getDataEvento());
        campanha.setTipoLogradouro(dto.getTipoLogradouro());
        campanha.setLogradouro(dto.getLogradouro());
        campanha.setNumero(dto.getNumero());
        campanha.setCep(dto.getCep());
        campanha.setBairro(dto.getBairro());
        campanha.setCidade(dto.getCidade());
        campanha.setUf(dto.getUf());
        campanha.setQuantidade(dto.getQuantidade());
        campanha.setItem(item);
        campanha.setAtivo(dto.isAtivo() != null ? dto.isAtivo() : true);
        campanha.setInstituicao(instituicao);
        campanha.setDataCadastro(LocalDate.now());
        campanhaRepository.save(campanha);
        // 3 - Registra a saída no estoque
        movimentacaoEstoqueService.registrarSaidaEstoque(
                item.getId(),
                instituicao.getId(),
                dto.getQuantidade(),
                TipoMovimentacao.SAIDA,
                "Saída de campanha: " + dto.getDescricao()
        );
        // 4 - Retorna DTO
        return new CampanhaResponseDTO(campanha);
    }
    @Transactional
    @Override
    public CampanhaResponseDTO update(Integer id, CampanhaUpdateDTO campanhaDTO) {
        Campanha campanha = campanhaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campanha não encontrada com id " + id ));
        Item item = itemRepository.findById(campanhaDTO.getItemId())
                .orElseThrow(() -> new ResourceNotFoundException("Item não encontrado para a campanha com id: " + campanhaDTO.getItemId()));
        Instituicao instituicao = instituicaoRepository.findById(campanhaDTO.getInstituicaoId())
                .orElseThrow(() -> new ResourceNotFoundException("Instituicção não encontrada para a campanha com id: " + campanhaDTO.getInstituicaoId()));
        if (campanhaDTO.getBairro() != null) {campanha.setBairro(campanhaDTO.getBairro());}
        if (campanhaDTO.getCidade() != null) {campanha.setCidade(campanhaDTO.getCidade());}
        if (campanhaDTO.getCep() != null) {campanha.setCep(campanhaDTO.getCep());}
        if (campanhaDTO.getDescricao() != null) {campanha.setDescricao(campanhaDTO.getDescricao());}
        if (campanhaDTO.getDataEvento() != null) {campanha.setDataEvento(campanhaDTO.getDataEvento());}
        if (campanhaDTO.getUf() != null) {campanha.setUf(campanhaDTO.getUf());}
        if (campanhaDTO.getTipoLogradouro() != null) {campanha.setTipoLogradouro(campanhaDTO.getTipoLogradouro());}
        if (campanhaDTO.isAtivo() != null) {campanha.setAtivo(campanhaDTO.isAtivo());}
        if (campanhaDTO.getLogradouro() != null) {campanha.setLogradouro(campanhaDTO.getLogradouro());}
        if (campanhaDTO.getNumero() != null) {campanha.setNumero(campanhaDTO.getNumero());}
        campanha.setItem(item);
        campanha.setInstituicao(instituicao);
        movimentacaoEstoqueService.movimentoByCampanhaUpdate(
                item.getId(),
                instituicao.getId(),
                campanha.getQuantidade(),
                TipoMovimentacao.UPDATE,
                "Atualização de campanha: ",
                campanhaDTO.getQuantidade()
        );
        if (campanhaDTO.getQuantidade() != null) {campanha.setQuantidade(campanhaDTO.getQuantidade());}
        campanhaRepository.save(campanha);
        return new CampanhaResponseDTO(campanha);
    }
    @Transactional
    @Override
    public void delete(Integer id) {
        Campanha campanha = campanhaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campanha não encontrada com id " + id));
        Item item = campanha.getItem();
        Instituicao instituicao = campanha.getInstituicao();
        movimentacaoEstoqueService.movimentoByCampanhaDelete(
            item.getId(),
            instituicao.getId(),
            campanha.getQuantidade(),
            TipoMovimentacao.UPDATE,
            "Exclusão de Campanha");
        campanhaRepository.delete(campanha);
        logger.info("Campanha ID {} deletada. {} unidades do item ID {} retornadas ao estoque da instituição ID {}",
                campanha.getId(), campanha.getQuantidade(), item.getId(), instituicao.getId());
    }
}
