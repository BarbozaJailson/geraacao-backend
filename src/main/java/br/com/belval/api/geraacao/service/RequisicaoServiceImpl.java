package br.com.belval.api.geraacao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.belval.api.geraacao.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.belval.api.geraacao.dto.RequisicaoCreateDTO;
import br.com.belval.api.geraacao.dto.RequisicaoResponseDTO;
import br.com.belval.api.geraacao.dto.RequisicaoUpdateDTO;
import br.com.belval.api.geraacao.model.Instituicao;
import br.com.belval.api.geraacao.model.Item;
import br.com.belval.api.geraacao.model.Requisicao;
import br.com.belval.api.geraacao.repository.InstituicaoRepository;
import br.com.belval.api.geraacao.repository.ItemRepository;
import br.com.belval.api.geraacao.repository.RequisicaoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class RequisicaoServiceImpl implements RequisicaoService{

    @Autowired
    private RequisicaoRepository requisicaoRepository;
    @Autowired
    private InstituicaoRepository instituicaoRepository;
    @Autowired
    private ItemRepository itemRepository;
    // Salvar uma nova requisição
    @Override
    @Transactional
    public RequisicaoResponseDTO criarRequisicao(RequisicaoCreateDTO dto) {
        Instituicao istituicao = instituicaoRepository.findById(dto.getInstituicaoId())
                .orElseThrow(() -> new ResourceNotFoundException("Instituição com id " + dto.getInstituicaoId() + " não encontrada"));
        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new ResourceNotFoundException("Item com id " + dto.getItemId() + " não encontrado"));
        Requisicao requisicao = new Requisicao();
        requisicao.setQuantidade(dto.getQuantidade());
        requisicao.setStatus("AGUAR. COLETA");
        requisicao.setData(LocalDate.now());
        requisicao.setAtivo(dto.isAtivo() != null ? dto.isAtivo() : true);
        requisicao.setInstituicao(istituicao);
        requisicao.setItem(item);
        requisicao = requisicaoRepository.save(requisicao);
        return new RequisicaoResponseDTO(requisicao);
    }
    // Atualizar requisição por id
    @Override
    @Transactional
    public RequisicaoResponseDTO atualizarRequisicao(Integer id, RequisicaoUpdateDTO dto) {
        Requisicao requisicao = requisicaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Requisicao com id " + id + " não encontrado"));
        if(dto.getStatus() != null) {requisicao.setStatus(dto.getStatus());}
        if(dto.isAtivo() != null) {requisicao.setAtivo(dto.isAtivo());}
        if(dto.getQuantidade() != null) {requisicao.setQuantidade(dto.getQuantidade());}
        if(dto.getInstituicaoId() != null) {
            Instituicao instituicao = instituicaoRepository.findById(dto.getInstituicaoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Instituicao com id " + dto.getInstituicaoId() + " não encontrada"));
            requisicao.setInstituicao(instituicao);
        }
        if(dto.getItemId() != null) {
            Item item = itemRepository.findById(dto.getItemId())
                    .orElseThrow(() -> new ResourceNotFoundException("Item com id " + dto.getItemId() + " não encontrado"));
            requisicao.setItem(item);
        }
        requisicao.setData(LocalDate.now());
        requisicao = requisicaoRepository.save(requisicao);
        return new RequisicaoResponseDTO(requisicao);
    }

    // Buscar as requisições por id
    @Override
    @Transactional(readOnly = true)
    public RequisicaoResponseDTO buscarPorId(Integer id) {
        Requisicao requisicao = requisicaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Requisição com id " + id + " não encontrado"));
        return new RequisicaoResponseDTO(requisicao);

    }

    // Busca todas as requisições
    @Override
    @Transactional(readOnly = true)
    public List<RequisicaoResponseDTO> listarTodos(){
        List<Requisicao> requisicao = requisicaoRepository.findAll();
        if(requisicao.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma requisição com id encontrada");
        }
        return requisicao.stream()
                .map(RequisicaoResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Excluir requisição
    @Override
    @Transactional
    public void excluir(Integer id) {
        if(!requisicaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Requisição com id " + id + " não encontrado");
        }
        requisicaoRepository.deleteById(id);
    }

    // Buscar requisições filtrada por instituição
    @Override
    @Transactional(readOnly = true)
    public List<RequisicaoResponseDTO> findByInstituicaoId(Integer idInstituicao){
        List<Requisicao> requisicao = requisicaoRepository.findByInstituicaoId(idInstituicao);
        if(requisicao.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma Requisicao com id " + idInstituicao + " encontrada");
        }
        return requisicao.stream()
                .map(RequisicaoResponseDTO::new)
                .collect(Collectors.toList());
    }

}

