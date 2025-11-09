package br.com.belval.api.geraacao.repository;

import br.com.belval.api.geraacao.model.Instituicao;
import br.com.belval.api.geraacao.model.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Integer> {
    List<MovimentacaoEstoque> findByInstituicao(Instituicao idInstituicao);
}
