package br.com.belval.api.geraacao.service;

import java.util.List;
import br.com.belval.api.geraacao.dto.RequisicaoCreateDTO;
import br.com.belval.api.geraacao.dto.RequisicaoResponseDTO;
import br.com.belval.api.geraacao.dto.RequisicaoUpdateDTO;

public interface RequisicaoService {

    RequisicaoResponseDTO criarRequisicao(RequisicaoCreateDTO dto);

    RequisicaoResponseDTO atualizarRequisicao(Integer id, RequisicaoUpdateDTO dto);

    RequisicaoResponseDTO buscarPorId(Integer id);

    List<RequisicaoResponseDTO> listarTodos();

    void excluir(Integer id);

    List<RequisicaoResponseDTO> findByInstituicaoId(Integer idInstituicao);
}
