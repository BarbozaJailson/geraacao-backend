package br.com.belval.api.geraacao.controller;

import br.com.belval.api.geraacao.dto.InstituicaoCreateDTO;
import br.com.belval.api.geraacao.dto.InstituicaoResponseDTO;
import br.com.belval.api.geraacao.dto.InstituicaoUpdateDTO;
import br.com.belval.api.geraacao.exception.InstituicaoAcessoNegadoException;
import br.com.belval.api.geraacao.model.TipoUser;
import br.com.belval.api.geraacao.model.Usuario;
import br.com.belval.api.geraacao.service.InstituicaoService;
import br.com.belval.api.geraacao.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/instituicao")
public class InstituicaoController {

    @Autowired
    private InstituicaoService instituicaoService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<InstituicaoResponseDTO>> getAll() {
        instituicaoService.listarTodos();
        return ResponseEntity.ok(instituicaoService.listarTodos());
    }
    //Busca Instituição pelo Id
    @GetMapping("/{id}")
    public ResponseEntity <?> getById(@PathVariable Integer id){
            return ResponseEntity.ok(instituicaoService.buscarPorId(id));
    }
    //Salva Instituição
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(@ModelAttribute @Valid InstituicaoCreateDTO dto) {
            return ResponseEntity.status(HttpStatus.CREATED).body(instituicaoService.criarInstituicao(dto));
    }
    //Busca Instituição pelo CNPJ
    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<?> getByCnpj(@PathVariable String cnpj) {
        return ResponseEntity.ok(instituicaoService.buscarPorCnpj(cnpj));
    }
    //Busca Instituição po nome
    @GetMapping("/nome/{nome}")
    public ResponseEntity<?> getByNome(@PathVariable String nome) {
        return ResponseEntity.ok(instituicaoService.buscarPorNome(nome));
    }
    //Atualiza Instituição pelo Id
    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> atualizarInstituicao(
            @PathVariable Integer id,
            @ModelAttribute @Valid InstituicaoUpdateDTO dto,
            Authentication authentication) {

        Usuario usuarioLogado = usuarioService.getUsuarioLogado(authentication);
        if (usuarioLogado.getTipoUser() == TipoUser.ADMIN_N2) {
            boolean pertence = usuarioLogado.getInstituicoes().stream()
                    .anyMatch(inst -> inst.getId().equals(id));
            if (!pertence) {
                throw new InstituicaoAcessoNegadoException(
                        "Você só pode alterar dados da sua própria instituição."
                );
            }
        }
        var instituicaoAtualizada = instituicaoService.atualizarInstituicao(id, dto);
        return ResponseEntity.ok(instituicaoAtualizada);
    }
    //Deleta Instituição pelo Id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
            instituicaoService.excluir(id);
            return ResponseEntity.noContent().build();
    }
}


