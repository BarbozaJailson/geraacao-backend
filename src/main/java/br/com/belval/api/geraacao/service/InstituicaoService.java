package br.com.belval.api.geraacao.service;

import java.util.List;
import br.com.belval.api.geraacao.dto.InstituicaoCreateDTO;
import br.com.belval.api.geraacao.dto.InstituicaoResponseDTO;
import br.com.belval.api.geraacao.dto.InstituicaoUpdateDTO;

public interface InstituicaoService {

    InstituicaoResponseDTO criarInstituicao(InstituicaoCreateDTO dto);

    InstituicaoResponseDTO buscarPorId(Integer id);

    List<InstituicaoResponseDTO> listarTodos();

    InstituicaoResponseDTO atualizarInstituicao(Integer id, InstituicaoUpdateDTO dto);

    void excluir(Integer id);

    InstituicaoResponseDTO buscarPorCnpj(String cnpj);

    List<InstituicaoResponseDTO> buscarPorNome(String nome);

}