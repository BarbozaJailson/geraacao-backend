package br.com.belval.api.geraacao.controller;

import br.com.belval.api.geraacao.dto.*;
import br.com.belval.api.geraacao.security.JwtUtil;
import br.com.belval.api.geraacao.service.UsuarioService;
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
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //Buscar todos os usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAll() {
        try {
            List<UsuarioResponseDTO> usuarios = usuarioService.listarTodos();
            return ResponseEntity.ok(usuarios);
        }catch(Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    //Buscar Usuario pelo Id
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            UsuarioResponseDTO usuario = usuarioService.buscarPorId(id);
            return ResponseEntity.ok(usuario);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> salvar(@ModelAttribute @Valid UsuarioCreateDTO dto) {
        try {
            UsuarioResponseDTO usuario = usuarioService.criarUsuario(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao registrar usuario: " + e.getMessage());
        }
    }

    //Atualizar usuario pelo Id
    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUsuario(
            @PathVariable Integer id,
            @ModelAttribute UsuarioUpdateDTO dto) {
        try {
            UsuarioResponseDTO usuario = usuarioService.atualizarUsuario(id, dto);
            return ResponseEntity.ok(usuario);
        }  catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    //Deletar Usuario pelo Id
    @DeleteMapping("/{id}")
    public ResponseEntity <Object> delete(@PathVariable Integer id){
        try {
            usuarioService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            if (e instanceof jakarta.persistence.EntityNotFoundException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar doacao: " + e.getMessage());
        }
    }

    //Buscar Usuario por tipoUser
    @GetMapping("/doadores/{tipoUser}")
    public ResponseEntity<?> getDoadores(@PathVariable String tipoUser) {
        try {
            List<UsuarioResponseDTO> doadores = usuarioService.buscaPorDoador(tipoUser);
            return ResponseEntity.ok(doadores);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno ao buscar usuários: " + e.getMessage());
        }
    }

    //Buscar Usuario por Instituição
    @GetMapping("/instituicoes/{idInstituicao}")
    public ResponseEntity<?> getUsuariosByInstituicao(@PathVariable Integer idInstituicao) {
        try {
            List<UsuarioResponseDTO> usuarios = usuarioService.buscarPorInstituicao(idInstituicao);
            return ResponseEntity.ok(usuarios);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno ao buscar usuários: " + e.getMessage());
        }
    }
}

