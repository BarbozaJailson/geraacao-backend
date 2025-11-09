package br.com.belval.api.geraacao.repository;

import br.com.belval.api.geraacao.model.Estoque;
import br.com.belval.api.geraacao.model.Instituicao;
import br.com.belval.api.geraacao.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {

    Optional<Estoque> findByItemAndInstituicao(Item item, Instituicao instituicao);
    Optional<Estoque> findByItem(Item item);
    Optional<Estoque> findByItemId(Integer itemId);
    List<Estoque> findByInstituicaoId(Integer idInstituicao);

}
