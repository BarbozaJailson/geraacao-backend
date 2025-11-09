package br.com.belval.api.geraacao.service;

import java.util.List;

import br.com.belval.api.geraacao.dto.EstoqueResponseDTO;
import br.com.belval.api.geraacao.model.Doacao;

public interface EstoqueService {
    List<EstoqueResponseDTO> buscarEstoqueComItens(Integer idInstituicao);

    EstoqueResponseDTO buscarEstoquePorId(Integer id);
}
