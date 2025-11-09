package br.com.belval.api.geraacao.repository;

import br.com.belval.api.geraacao.model.Doacao;
import br.com.belval.api.geraacao.model.Requisicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RequisicaoRepository extends JpaRepository<Requisicao, Integer> {
    List<Requisicao> findByInstituicaoId(Integer idInstituicao);

    @Query("SELECT r FROM Requisicao r " +
            "join r.item i " +
            "WHERE LOWER(i.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Requisicao> findByItemNome(@Param("nome") String nome);
}
