package br.com.belval.api.geraacao.controller;

import br.com.belval.api.geraacao.dto.EstoqueResponseDTO;
import br.com.belval.api.geraacao.service.EstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/estoque")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    // Busca estoque por Instituição
    @GetMapping("/instituicao/{idInstituicao}")
    public ResponseEntity<List<EstoqueResponseDTO>> listarEstoquePorInstituicao(@PathVariable Integer idInstituicao) {
        List<EstoqueResponseDTO> estoques = estoqueService.buscarEstoqueComItens(idInstituicao);
        return ResponseEntity.ok(estoques);
    }

    // Busca estoque pelo Id
    @GetMapping("/{idEstoque}")
    public ResponseEntity<EstoqueResponseDTO> buscaEstoquePorId(@PathVariable Integer idEstoque){
        return ResponseEntity.ok(estoqueService.buscarEstoquePorId(idEstoque));
    }
}

