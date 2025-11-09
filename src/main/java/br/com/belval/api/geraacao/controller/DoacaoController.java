package br.com.belval.api.geraacao.controller;

import br.com.belval.api.geraacao.dto.DoacaoCreateDTO;
import br.com.belval.api.geraacao.dto.DoacaoResponseDTO;
import br.com.belval.api.geraacao.dto.DoacaoUpdateDTO;
import br.com.belval.api.geraacao.model.Doacao;
import br.com.belval.api.geraacao.service.DoacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/doacoes")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    @GetMapping
    public ResponseEntity<List<DoacaoResponseDTO>> getAll() {
        return ResponseEntity.ok(doacaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoacaoResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(doacaoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<DoacaoResponseDTO> save(@Valid @RequestBody DoacaoCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doacaoService.criarDoacao(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoacaoResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody DoacaoUpdateDTO dto) {
        return ResponseEntity.ok(doacaoService.atualizarDoacao(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        doacaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<DoacaoResponseDTO>> getDoacoesByUsuario(@PathVariable Integer id) {
        List<DoacaoResponseDTO> doacoes = doacaoService.buscarPorDoador(id);
        return doacoes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(doacoes);
    }

    @GetMapping("/instituicao/{idInstituicao}")
    public ResponseEntity<List<DoacaoResponseDTO>> getDoacoesByInstituicao(@PathVariable Long idInstituicao) {
        List<DoacaoResponseDTO> doacoes = doacaoService.buscarPorInstituicao(idInstituicao);
        return doacoes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(doacoes);
    }

    @PutMapping("/{id}/{status}")
    public ResponseEntity<?> atualizarStatus(
            @PathVariable Integer id,
            @PathVariable String status) { // <- agora usa PathVariable
        try {
            Doacao doacaoAtualizada = doacaoService.atualizarStatus(id, status);
            return ResponseEntity.ok(doacaoAtualizada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
