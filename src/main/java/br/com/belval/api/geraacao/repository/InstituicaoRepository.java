package br.com.belval.api.geraacao.repository;

import br.com.belval.api.geraacao.model.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Integer> {
    Optional<Instituicao> findByCnpj(String cnpj);

    @Query("SELECT i FROM Instituicao i WHERE LOWER(i.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Instituicao> findByInstituicaoNome(@Param("nome") String nome);

}
