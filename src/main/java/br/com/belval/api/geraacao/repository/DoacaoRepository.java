package br.com.belval.api.geraacao.repository;

import br.com.belval.api.geraacao.model.Doacao;
import br.com.belval.api.geraacao.model.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Integer> {
    List<Doacao> findByUsuarioId(Integer id);
    //	 Exemplo se Doacao tiver um relacionamento com Requisicao, e Requisicao tiver um com Instituicao
    @Query("SELECT d FROM Doacao d WHERE d.requisicao.instituicao.id = :idInstituicao")
    List<Doacao> findByInstituicaoId(@Param("idInstituicao") Long idInstituicao);

    @Query("SELECT d FROM Doacao d " +
            "join d.requisicao r " +
            "join r.item i " +
                "WHERE LOWER(i.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Doacao> findByItemNome(@Param("nome") String nome);
}
