package br.com.belval.api.geraacao.repository;

import br.com.belval.api.geraacao.dto.CampanhaResponseDTO;
import br.com.belval.api.geraacao.model.Campanha;
import br.com.belval.api.geraacao.model.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CampanhaRepository extends JpaRepository<Campanha, Integer> {
    List<Campanha> findByInstituicaoId(Integer instituicaoId);

    // Encontrar pelo campo de pesquisa
    @Query("SELECT c FROM Campanha c WHERE LOWER(c.descricao) LIKE LOWER(CONCAT('%', :descricao, '%'))")
    List<Campanha> findByCampanhaDesc(@Param("descricao") String nome);
}
