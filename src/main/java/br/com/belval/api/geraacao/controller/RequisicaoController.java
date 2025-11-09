package br.com.belval.api.geraacao.controller;

import br.com.belval.api.geraacao.dto.RequisicaoCreateDTO;
import br.com.belval.api.geraacao.dto.RequisicaoResponseDTO;
import br.com.belval.api.geraacao.dto.RequisicaoUpdateDTO;
import br.com.belval.api.geraacao.service.RequisicaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/requisicoes")
public class RequisicaoController {

    @Autowired
    private RequisicaoService requisicaoService;

    //Busca todas as Requisição
    @GetMapping
    public ResponseEntity<?> getAll() {
            return ResponseEntity.ok(requisicaoService.listarTodos());
    }
    //Busca Requisições pelo Id
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
            return ResponseEntity.ok(requisicaoService.buscarPorId(id));
    }
    //Salvar Requisições
    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody RequisicaoCreateDTO dto) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(requisicaoService.criarRequisicao(dto));
    }
    //Atualizar Requisições pelo Id
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @Valid @RequestBody RequisicaoUpdateDTO dto) {
            return ResponseEntity.ok(requisicaoService.atualizarRequisicao(id, dto));
    }
    //Deletar pelo Id
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
            requisicaoService.excluir(id);
            return ResponseEntity.noContent().build();
    }
    //Buscar Requisição pela Instituição
    @GetMapping("/instituicao/{idInstituicao}")
    public ResponseEntity<?> listarRequisicaoPorInstituicao(@PathVariable Integer idInstituicao) {
            return ResponseEntity.ok(requisicaoService.findByInstituicaoId(idInstituicao));
    }
}

