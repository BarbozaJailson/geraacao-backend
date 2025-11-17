package br.com.belval.api.geraacao.service;

import java.util.List;
import java.util.stream.Collectors;
import br.com.belval.api.geraacao.dto.DoacaoResponseDTO;
import br.com.belval.api.geraacao.dto.InstituicaoResponseDTO;
import br.com.belval.api.geraacao.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import br.com.belval.api.geraacao.dto.EstoqueResponseDTO;
import br.com.belval.api.geraacao.model.Estoque;
import br.com.belval.api.geraacao.repository.EstoqueRepository;

@Service
public class EstoqueServiceImpl implements EstoqueService {

    @Value("${app.base-url}")
    private String baseUrl;
    @Autowired
    private final EstoqueRepository estoqueRepository;

    public EstoqueServiceImpl(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    // Metodo para buscar estoque por instituição
    @Override
    public List<EstoqueResponseDTO> buscarEstoqueComItens(Integer idInstituicao) {
        if (idInstituicao == null || idInstituicao <= 0) {
            throw new IllegalArgumentException("ID da instituição inválido");
        }
        List<Estoque> estoques = estoqueRepository.findByInstituicaoId(idInstituicao);
        if (estoques.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum estoque encontrado para a instituição com ID " + idInstituicao);
        }
        return estoques.stream()
                .map(est -> {
                    EstoqueResponseDTO response = new EstoqueResponseDTO(est);
                    if (response.getItemImagem() != null) {
                        response.setItemImagem(baseUrl + response.getItemImagem());
                    }
                    return response;
                })
                .collect(Collectors.toList());
    }

    //Metodo para buscar estoque porId
    @Override
    public EstoqueResponseDTO buscarEstoquePorId(Integer id) {
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estoque com ID " + id + " não encontrado"));
        EstoqueResponseDTO response = new EstoqueResponseDTO(estoque);
            if (response.getItemImagem() != null) {
                response.setItemImagem(baseUrl + response.getItemImagem());
           }
            return response;
    }
}

