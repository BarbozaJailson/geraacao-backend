package br.com.belval.api.geraacao.service;

import br.com.belval.api.geraacao.dto.DoacaoCreateDTO;
import br.com.belval.api.geraacao.dto.DoacaoResponseDTO;
import br.com.belval.api.geraacao.dto.DoacaoUpdateDTO;
import br.com.belval.api.geraacao.dto.RequisicaoResponseDTO;
import br.com.belval.api.geraacao.exception.ResourceNotFoundException;
import br.com.belval.api.geraacao.model.Doacao;
import br.com.belval.api.geraacao.model.Requisicao;
import br.com.belval.api.geraacao.model.TipoMovimentacao;
import br.com.belval.api.geraacao.model.Usuario;
import br.com.belval.api.geraacao.repository.DoacaoRepository;
import br.com.belval.api.geraacao.repository.RequisicaoRepository;
import br.com.belval.api.geraacao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoacaoServiceImpl implements DoacaoService{

    @Value("${app.base-url}")
    private String baseUrl;
    @Autowired
    private DoacaoRepository doacaoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RequisicaoRepository requisicaoRepository;
    @Autowired
    private MovimentacaoEstoqueService movimentacaoEstoqueService;

    // Salvar uma nova doação
    @Override
    @Transactional
    public DoacaoResponseDTO criarDoacao(DoacaoCreateDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com id " + dto.getUsuarioId() + " não encontrado."));
        Requisicao requisicao = requisicaoRepository.findById(dto.getRequisicaoId())
                .orElseThrow(() -> new ResourceNotFoundException("Requisição com id " + dto.getRequisicaoId() + " não encontrada."));
        if(dto.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade da doação deve ser maior que zero.");
        }
        if(dto.getQuantidade() > requisicao.getSaldo()) {
            throw new IllegalArgumentException("Quantidade maior que o saldo disponível da requisição.");
        }
        Doacao doacao = new Doacao();
        doacao.setStatus(dto.getStatus() != null ? dto.getStatus() : "Pendente");
        doacao.setQuantidade(dto.getQuantidade());
        doacao.setUsuario(usuario);
        doacao.setRequisicao(requisicao);
        //doacao.setStatus(dto.getStatus());
        doacao.setAtivo(dto.isAtivo() != null ? dto.isAtivo() : true);
        Doacao doacaoSalva = doacaoRepository.save(doacao);
        //int saldo = requisicao.getSaldo();
        requisicao.adicionarDoacao(dto.getQuantidade());
        requisicaoRepository.save(requisicao);

        DoacaoResponseDTO response = new DoacaoResponseDTO(doacaoSalva);
        if (response.getItemImagem() != null) {
            response.setItemImagem(baseUrl + response.getItemImagem());
        }
        return response;
    }

    // Atualizar doação por id
    @Override
    @Transactional
    public DoacaoResponseDTO atualizarDoacao(Integer id, DoacaoUpdateDTO dto) {
        Doacao doacao = doacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doação com id " + id + " não encontrada"));

        String statusAnterior = doacao.getStatus();

        if (dto.getStatus() != null) {
            doacao.setStatus(dto.getStatus());
        }
        if (dto.getQuantidade() != null) {
            doacao.setQuantidade(dto.getQuantidade());
        }
        if (dto.isAtivo() != null){
            doacao.setAtivo(dto.isAtivo());
        }
        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário com id " + dto.getUsuarioId() + " não encontrado"));
            doacao.setUsuario(usuario);
        }
        if (dto.getRequisicaoId() != null) {
            Requisicao requisicao = requisicaoRepository.findById(dto.getRequisicaoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Requisição com id " + dto.getRequisicaoId() + " não encontrada"));
            doacao.setRequisicao(requisicao);
        }

        // 🔽 Só cria movimentação se a instituição mudar o status para FINALIZADA
        if (dto.getStatus() != null
                && dto.getStatus().equalsIgnoreCase("Finalizada")
                && (statusAnterior == null || !statusAnterior.equalsIgnoreCase("Finalizada"))) {

            if (doacao.getRequisicao() != null
                    && doacao.getRequisicao().getItem() != null
                    && doacao.getRequisicao().getInstituicao() != null) {

                movimentacaoEstoqueService.criarMovimentacao(
                        doacao.getRequisicao().getItem().getId(),
                        doacao.getRequisicao().getInstituicao().getId(),
                        doacao.getRequisicao().getId(),
                        doacao.getId(),
                        TipoMovimentacao.ENTRADA,
                        doacao.getQuantidade()
                );
            } else {
                throw new IllegalStateException("Requisição, item ou instituição ausente para criar movimentação.");
            }
        }

        Doacao newDoacao = doacaoRepository.save(doacao);
        DoacaoResponseDTO response = new DoacaoResponseDTO(newDoacao);
        if (response.getItemImagem() != null) {
            response.setItemImagem(baseUrl + response.getItemImagem());
        }
        return response;
    }

    // buscar doações por id
    @Override
    @Transactional(readOnly = true)
    public DoacaoResponseDTO buscarPorId(Integer id) {
        Doacao doacao = doacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doação com id " + id + " não encontrada."));
        DoacaoResponseDTO response = new DoacaoResponseDTO(doacao);
        if (response.getItemImagem() != null) {
            response.setItemImagem(baseUrl + response.getItemImagem());
        }
        return response;
    }

    // Buscar todas as doações
    @Override
    @Transactional(readOnly = true)
    public List<DoacaoResponseDTO> listarTodos(){
        List<Doacao> doacoes = doacaoRepository.findAll();
        if (doacoes.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma doação encontrada");
        }
        return doacoes.stream()
                .map(doac -> {
                    DoacaoResponseDTO response = new DoacaoResponseDTO(doac);
                    if (response.getItemImagem() != null) {
                        response.setItemImagem(baseUrl + response.getItemImagem());
                    }
                    return response;
                })
                .collect(Collectors.toList());
    }

    // Excluir doação por id
    @Override
    @Transactional
    public void excluir(Integer id) {
        if (!doacaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Doação com id " + id + " não encontrada");
        }
        doacaoRepository.deleteById(id);
    }

    // Buscar doações filtradas por doador
    @Override
    @Transactional(readOnly = true)
    public List<DoacaoResponseDTO> buscarPorDoador(@PathVariable Integer id) {
        List<Doacao> doacoes = doacaoRepository.findByUsuarioId(id);
        if(doacoes.isEmpty()) {
            throw new ResourceNotFoundException("Doacao do usuario com id " + id + " não encontrada");
        }
        return doacoes.stream()
                .map(doac -> {
                    DoacaoResponseDTO response = new DoacaoResponseDTO(doac);
                    if (response.getItemImagem() != null) {
                        response.setItemImagem(baseUrl + response.getItemImagem());
                    }
                    return response;
                })
                .collect(Collectors.toList());
    }

    // Buscar doações filtradas por instituição
    @Override
    @Transactional(readOnly = true)
    public List<DoacaoResponseDTO> buscarPorInstituicao(@PathVariable Long idInstituicao) {
        List<Doacao> doacoes = doacaoRepository.findByInstituicaoId(idInstituicao);
        if(doacoes.isEmpty()) {
            throw new ResourceNotFoundException("Doacao da instituicao com id " + idInstituicao + " não encontrado");
        }
        return doacoes.stream()
                .map(doac -> {
                    DoacaoResponseDTO response = new DoacaoResponseDTO(doac);
                    if (response.getItemImagem() != null) {
                        response.setItemImagem(baseUrl + response.getItemImagem());
                    }
                       return response;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public DoacaoResponseDTO atualizarStatus(Integer id, String status) {
        Doacao doacao = doacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doação não encontrada: " + id));

        String statusAnterior = doacao.getStatus();
        doacao.setStatus(status);
        doacao = doacaoRepository.save(doacao);

        if (status.equalsIgnoreCase("Cancelada") && !statusAnterior.equalsIgnoreCase("Cancelada")) {
            // Reverte estoque
            movimentacaoEstoqueService.criarMovimentacao(
                    doacao.getRequisicao().getItem().getId(),
                    doacao.getRequisicao().getInstituicao().getId(),
                    doacao.getRequisicao().getId(),
                    doacao.getId(),
                    TipoMovimentacao.SAIDA,
                    doacao.getQuantidade()
            );
        }

        DoacaoResponseDTO response = new DoacaoResponseDTO(doacao);
        if (response.getItemImagem() != null) {
            response.setItemImagem(baseUrl + response.getItemImagem());
        }
        return response;
    }

}

