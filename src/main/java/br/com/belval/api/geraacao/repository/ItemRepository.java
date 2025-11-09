package br.com.belval.api.geraacao.repository;

import br.com.belval.api.geraacao.model.Instituicao;
import br.com.belval.api.geraacao.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    //List<Item> findByInstituicaoId(Integer idInstituicao);
    @Query("SELECT i FROM Item i WHERE LOWER(i.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Item> findByItemNome(@Param("nome") String nome);
}
