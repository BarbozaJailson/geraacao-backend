package br.com.belval.api.geraacao.controller;

import br.com.belval.api.geraacao.dto.MovimentacaoEstoqueCreateDTO;
import br.com.belval.api.geraacao.dto.MovimentacaoEstoqueResponseDTO;
import br.com.belval.api.geraacao.service.MovimentacaoEstoqueService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/movimentacaoestoque")
@CrossOrigin("http://localhost:5173")
public class MovimentacaoEstoqueController {

    private final MovimentacaoEstoqueService movService;

    public MovimentacaoEstoqueController(MovimentacaoEstoqueService movService) {
        this.movService = movService;
    }

    // Salvar movimentação
    @PostMapping
    public ResponseEntity<?> registrarSaidaEstoque(@Valid @RequestBody MovimentacaoEstoqueCreateDTO dto) {
            return ResponseEntity.status(HttpStatus.CREATED).body(movService.registrarSaidaEstoque(
                    dto.getItemId(), dto.getInstituicaoId(), dto.getQuantidade(), dto.getTipoMovimentacao(), dto.getObservacao()));
    }
    // Busca movimentações por Instituição
    @GetMapping("/instituicao/{idInstituicao}")
    public ResponseEntity<?> buscaPorInstituicao(@PathVariable Integer idInstituicao) {
            return ResponseEntity.ok(movService.BuscarPorInstituicao(idInstituicao));
    }
    // Busca movimetações por Id
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
            return ResponseEntity.ok(movService.buscarPorId(id));
    }
}

