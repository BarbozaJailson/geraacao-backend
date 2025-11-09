package br.com.belval.api.geraacao.service;

import java.util.List;
import br.com.belval.api.geraacao.dto.ItemCreateDTO;
import br.com.belval.api.geraacao.dto.ItemResponseDTO;
import br.com.belval.api.geraacao.dto.ItemUpdateDTO;

public interface ItemService {

    ItemResponseDTO criarItem(ItemCreateDTO dto);

    ItemResponseDTO atualizarItem(Integer id, ItemUpdateDTO dto);

    ItemResponseDTO buscarPorId(Integer id);

    List<ItemResponseDTO> listarTodos();

    void excluir(Integer id);
}
