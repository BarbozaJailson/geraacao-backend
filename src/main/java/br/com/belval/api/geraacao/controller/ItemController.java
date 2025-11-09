package br.com.belval.api.geraacao.controller;

import br.com.belval.api.geraacao.dto.ItemCreateDTO;
import br.com.belval.api.geraacao.dto.ItemResponseDTO;
import br.com.belval.api.geraacao.dto.ItemUpdateDTO;
import br.com.belval.api.geraacao.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    //Fazer busca completa no banco de dados
    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> getAll() {
        return ResponseEntity.ok(itemService.listarTodos());
    }

    //Buscar Item pelo Id
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
            return ResponseEntity.ok(itemService.buscarPorId(id));
    }

    //Inserir Item no banco
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> salvarItem(@ModelAttribute @Valid ItemCreateDTO dto) {
            return ResponseEntity.status(HttpStatus.CREATED).body(itemService.criarItem(dto));
    }

    //Atualizar Item pelo Id
    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> atualizarItem(@PathVariable Integer id, @ModelAttribute @Valid ItemUpdateDTO dto) {
            return ResponseEntity.ok(itemService.atualizarItem(id, dto));
    }

    //Deletar Item pelo Id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
            itemService.excluir(id);
            return ResponseEntity.noContent().build();
    }
}

