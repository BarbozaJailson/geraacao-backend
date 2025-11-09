package br.com.belval.api.geraacao.service;

import java.util.List;
import java.util.stream.Collectors;
import br.com.belval.api.geraacao.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.belval.api.geraacao.dto.EstoqueResponseDTO;
import br.com.belval.api.geraacao.model.Estoque;
import br.com.belval.api.geraacao.repository.EstoqueRepository;

@Service
public class EstoqueServiceImpl implements EstoqueService {

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
        if (estoques == null || estoques.isEmpty()) {
            throw new IllegalStateException("Nenhum estoque encontrado para a instituição com id " + idInstituicao);
        }
        return estoques.stream()
                .map(EstoqueResponseDTO::new)
                .collect(Collectors.toList());
    }

    //Metodo para buscar estoque porId
    @Override
    public EstoqueResponseDTO buscarEstoquePorId(Integer id) {
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estoque com id: " + id + " não encontrado"));
        return new EstoqueResponseDTO(estoque);
    }
}

