package br.com.belval.api.geraacao.service;

import java.util.List;
import br.com.belval.api.geraacao.dto.MovimentacaoEstoqueResponseDTO;
import br.com.belval.api.geraacao.model.TipoMovimentacao;

public interface MovimentacaoEstoqueService {

    MovimentacaoEstoqueResponseDTO criarMovimentacao(Integer idItem, Integer idInstituicao, Integer requisicao, Integer doacao, TipoMovimentacao tipoMovimentacao, int quantidade);

    List<MovimentacaoEstoqueResponseDTO> BuscarPorInstituicao(Integer idInstituicao);

    MovimentacaoEstoqueResponseDTO buscarPorId(Integer id);

    MovimentacaoEstoqueResponseDTO registrarSaidaEstoque(Integer idItem, Integer idInstituicao, Integer quantidade, TipoMovimentacao tipoMovimentacao, String observacao);

    MovimentacaoEstoqueResponseDTO movimentoByCampanhaDelete(Integer itemId, Integer instituicaoId, Integer quantidade, TipoMovimentacao tipoMovimentacao, String observação);

    MovimentacaoEstoqueResponseDTO movimentoByCampanhaUpdate(Integer itemId, Integer instituicaoId, Integer qtdCampaanha, TipoMovimentacao tipoMovimentacao, String observacao, Integer novaQtd);

    }
