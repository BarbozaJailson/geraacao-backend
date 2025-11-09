package br.com.belval.api.geraacao.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import br.com.belval.api.geraacao.exception.ResourceNotFoundException;
import br.com.belval.api.geraacao.model.TipoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.belval.api.geraacao.dto.UsuarioCreateDTO;
import br.com.belval.api.geraacao.dto.UsuarioResponseDTO;
import br.com.belval.api.geraacao.dto.UsuarioUpdateDTO;
import br.com.belval.api.geraacao.model.Instituicao;
import br.com.belval.api.geraacao.model.Usuario;
import br.com.belval.api.geraacao.repository.InstituicaoRepository;
import br.com.belval.api.geraacao.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private static final String UPLOAD_DIR = "uploads/";
    @Autowired
    private final InstituicaoRepository instituicaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // Injeção via construtor (recomendada)
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder, InstituicaoRepository instituicaoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.instituicaoRepository = instituicaoRepository;
    }

    @Override
    @Transactional
    public UsuarioResponseDTO criarUsuario(@Valid UsuarioCreateDTO dto) {
        try {
            if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
                throw new IllegalArgumentException("E-mail já cadastrado");
            }
            String fileName = null;
            if (dto.getImagem() != null && !dto.getImagem().isEmpty()) {
                fileName = salvarImagem(dto.getImagem());
            }
            Usuario usuario = new Usuario();
            usuario.setBairro(dto.getBairro());
            usuario.setCep(dto.getCep());
            usuario.setCidade(dto.getCidade());
            usuario.setCpf(dto.getCpf());
            usuario.setDataNascimento(dto.getDataNascimento());
            usuario.setEmail(dto.getEmail());
            usuario.setLogradouro(dto.getLogradouro());
            usuario.setNumero(dto.getNumero());
            usuario.setNome(dto.getNome());
            usuario.setTelefone(dto.getTelefone());
            usuario.setTipoLogradouro(dto.getTipoLogradouro());
            usuario.setTipoUser(TipoUser.valueOf(dto.getTipoUser()));
            usuario.setUf(dto.getUf());
            usuario.setAtivo(dto.isAtivo() != null ? dto.isAtivo() : true);
            usuario.setImagem(fileName != null ? "/uploads/" + fileName : null);
            usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
            if (usuario.getTipoUser() != TipoUser.DOADOR && dto.getInstituicaoId() != null) {
                Instituicao instituicao = instituicaoRepository.findById(dto.getInstituicaoId())
                        .orElseThrow(() -> new ResourceNotFoundException("Instituição não encontrada"));
                usuario.getInstituicoes().add(instituicao);
            }
            Usuario novoUsuario = usuarioRepository.save(usuario);
            return new UsuarioResponseDTO(novoUsuario);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar imagem: " + e.getMessage(), e);
        }
    }
    @Override
    @Transactional
    public UsuarioResponseDTO atualizarUsuario(Integer id, UsuarioUpdateDTO dto) {
        try {
            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário com id " + id + " não encontrado"));
            if (dto.getBairro() != null) { usuario.setBairro(dto.getBairro()); }
            if (dto.getCep() != null) { usuario.setCep(dto.getCep()); }
            if (dto.getCidade() != null) { usuario.setCidade(dto.getCidade()); }
            if (dto.getCpf() != null) { usuario.setCpf(dto.getCpf()); }
            if (dto.getDataNascimento() != null) { usuario.setDataNascimento(dto.getDataNascimento()); }
            if (dto.getEmail() != null) { usuario.setEmail(dto.getEmail()); }
            if (dto.getLogradouro() != null) { usuario.setLogradouro(dto.getLogradouro()); }
            if (dto.getNome() != null) { usuario.setNome(dto.getNome()); }
            if (dto.getNumero() != null) { usuario.setNumero(dto.getNumero()); }
            if (dto.getTelefone() != null) { usuario.setTelefone(dto.getTelefone()); }
            if (dto.getTipoLogradouro() != null) { usuario.setTipoLogradouro(dto.getTipoLogradouro()); }
            if (dto.getTipoUser() != null) { usuario.setTipoUser(TipoUser.valueOf(dto.getTipoUser())); }
            if (dto.getUf() != null) { usuario.setUf(dto.getUf()); }
            if (dto.isAtivo() != null) { usuario.setAtivo(dto.isAtivo()); }
            if (dto.getNovaSenha() != null && !dto.getNovaSenha().isEmpty()) {
                // verifica senha antiga
                if (dto.getSenha() == null || !passwordEncoder.matches(dto.getSenha(), usuario.getSenha())) {
                    throw new SecurityException("Senha antiga incorreta");
                }
                // atualiza para nova senha
                usuario.setSenha(passwordEncoder.encode(dto.getNovaSenha()));
            }
            if (dto.getImagem() != null && !dto.getImagem().isEmpty()) {
                if (usuario.getImagem() != null) {
                    String caminhoAntigo = usuario.getImagem();
                    String nomeArquivoAntigo = caminhoAntigo.replace("/uploads/", "");
                    Path caminhoArquivoAntigo = Paths.get(UPLOAD_DIR).resolve(nomeArquivoAntigo).toAbsolutePath();
                    try {
                        Files.deleteIfExists(caminhoArquivoAntigo);
                    } catch (IOException e) {
                        System.err.println("Falha ao deletar arquivo antigo: " + caminhoArquivoAntigo + " - " + e.getMessage());
                    }
                }
                String fileName = salvarImagem(dto.getImagem());
                usuario.setImagem("/uploads/" + fileName);
            }
            Usuario usuarioAtualizado = usuarioRepository.save(usuario);
            return new UsuarioResponseDTO(usuarioAtualizado);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar imagem: " + e.getMessage(), e);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage(), e);
        }
    }
    //Salvar as imagens
    private String salvarImagem(MultipartFile imagem) throws IOException {
        if (imagem == null || imagem.isEmpty()) return null;
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) uploadDir.mkdirs();
        String fileName = System.currentTimeMillis() + "_" + imagem.getOriginalFilename();
        Path uploadPath = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();
        Path filePath = uploadPath.resolve(fileName);
        Files.write(filePath, imagem.getBytes());
        return fileName;
    }
    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarPorId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario com id " + id + " não encontrado"));
        return new UsuarioResponseDTO(usuario);
    }
    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarTodos(){
        List<Usuario> usuario = usuarioRepository.findAll();
        if(usuario.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum suario encontrado");
        }
        return usuario.stream()
                .map(UsuarioResponseDTO::new)
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public void excluir(Integer id) {
        if(!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario com id " + id + " não encontrado");
        }
        usuarioRepository.deleteById(id);
    }
    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> buscaPorDoador(String tipoUserStr) {
        TipoUser tipoUser = TipoUser.valueOf(tipoUserStr.toUpperCase());
        List<Usuario> usuarios = usuarioRepository.findByTipoUser(tipoUser);
        System.out.println(usuarios);
        if (usuarios.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum usuário com tipo '" + tipoUser + "' encontrado");
        }
        return usuarios.stream()
                .map(UsuarioResponseDTO::new)
                .collect(Collectors.toList());
    }
    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> buscarPorInstituicao(Integer idInstituicao){
        if (idInstituicao == null || idInstituicao <= 0) {
            throw new IllegalArgumentException("ID da instituição inválido");
        }
        List<Usuario> usuarios = usuarioRepository.findByInstituicoes_Id(idInstituicao);
        if(usuarios.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum usuario para a instituição com  id " + idInstituicao + " encontrado");
        }
        return usuarios.stream()
                .map(UsuarioResponseDTO::new)
                .collect(Collectors.toList());
    }
    @Override
    @Transactional(readOnly = true)
    public Usuario loginEntity(String login, String senha, String cliente) {
        Usuario usuario = usuarioRepository.findByEmail(login)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        // Verifica senha usando BCrypt
        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new SecurityException("Senha incorreta");
        }

        // Verifica tipo de usuário permitido
        TipoUser tipo = usuario.getTipoUser();
        if ("web".equalsIgnoreCase(cliente) && tipo != TipoUser.ADMIN_N1 && tipo != TipoUser.ADMIN_N2 && tipo != TipoUser.GERENCIADOR) {
            throw new SecurityException("Tipo de usuário não autorizado");
        }
        if ("app".equalsIgnoreCase(cliente) && tipo != TipoUser.DOADOR) {
            throw new SecurityException("Tipo de usuário não autorizado");
        }
        return usuario; // Retorna a entity
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioLogado(Authentication authentication) {
        if (authentication == null || authentication.getName() == null) {
            throw new SecurityException("Usuário não autenticado");
        }

        String email = authentication.getName(); // O Spring guarda o username aqui

        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado para o e-mail: " + email));
    }


}

