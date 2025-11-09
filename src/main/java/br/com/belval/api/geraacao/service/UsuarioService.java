package br.com.belval.api.geraacao.service;

import java.util.List;
import br.com.belval.api.geraacao.dto.UsuarioCreateDTO;
import br.com.belval.api.geraacao.dto.UsuarioResponseDTO;
import br.com.belval.api.geraacao.dto.UsuarioUpdateDTO;
import br.com.belval.api.geraacao.model.Usuario;
import org.springframework.security.core.Authentication;

public interface UsuarioService {

    UsuarioResponseDTO criarUsuario(UsuarioCreateDTO dto);

    UsuarioResponseDTO atualizarUsuario(Integer id, UsuarioUpdateDTO dto);

    UsuarioResponseDTO buscarPorId(Integer id);

    List<UsuarioResponseDTO> listarTodos();

    void excluir(Integer id);

    List<UsuarioResponseDTO> buscaPorDoador(String tipoUser);

    List<UsuarioResponseDTO> buscarPorInstituicao(Integer idInstituicao);

    Usuario loginEntity(String login, String senha, String cliente);

    Usuario getUsuarioLogado(Authentication authentication);
}
