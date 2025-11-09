package br.com.belval.api.geraacao.controller;

import br.com.belval.api.geraacao.dto.CampanhaCreateDTO;
import br.com.belval.api.geraacao.dto.CampanhaResponseDTO;
import br.com.belval.api.geraacao.dto.CampanhaUpdateDTO;
import br.com.belval.api.geraacao.service.CampanhaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campanha")
public class CampanhaController {

    @Autowired
    private CampanhaService campanhaService;

    @GetMapping
    public ResponseEntity<List<CampanhaResponseDTO>> findAll() {
        return ResponseEntity.ok(campanhaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampanhaResponseDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(campanhaService.findById(id));
    }

    @GetMapping("/instituicao/{instituicaoId}")
    public ResponseEntity<List<CampanhaResponseDTO>> findByInstituicao(@PathVariable Integer instituicaoId) {
        return ResponseEntity.ok(campanhaService.findByInstituicaoId(instituicaoId));
    }

    @PostMapping
    public ResponseEntity<CampanhaResponseDTO> save(@Valid @RequestBody CampanhaCreateDTO dto) {
        return ResponseEntity.ok(campanhaService.criarCampanha(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampanhaResponseDTO> update(@Valid @RequestBody CampanhaUpdateDTO dto, @PathVariable Integer id) {
        return ResponseEntity.ok(campanhaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        campanhaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
