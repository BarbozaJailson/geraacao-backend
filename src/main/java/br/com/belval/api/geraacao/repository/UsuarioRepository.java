package br.com.belval.api.geraacao.repository;

import br.com.belval.api.geraacao.model.Instituicao;
import br.com.belval.api.geraacao.model.TipoUser;
import br.com.belval.api.geraacao.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByTipoUser(TipoUser tipoUser);
    List<Usuario> findByInstituicoes_Id(Integer idInstituicao);

    @Query("SELECT u FROM Usuario u WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Usuario> findByUsuarioNome(@Param("nome") String nome);
}
