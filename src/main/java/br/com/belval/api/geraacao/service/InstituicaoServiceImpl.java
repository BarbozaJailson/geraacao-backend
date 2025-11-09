package br.com.belval.api.geraacao.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import br.com.belval.api.geraacao.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.belval.api.geraacao.dto.InstituicaoCreateDTO;
import br.com.belval.api.geraacao.dto.InstituicaoResponseDTO;
import br.com.belval.api.geraacao.dto.InstituicaoUpdateDTO;
import br.com.belval.api.geraacao.model.Instituicao;
import br.com.belval.api.geraacao.repository.InstituicaoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class InstituicaoServiceImpl implements InstituicaoService{

    @Autowired
    private InstituicaoRepository instituicaoRepository;
    private static final String UPLOAD_DIR = "uploads/";
    // Salvar uma nova instituição
    @Override
    public InstituicaoResponseDTO criarInstituicao(InstituicaoCreateDTO dto) {
        try {
            String fileName = salvarImagem(dto.getImagem());
            Instituicao instituicao = new Instituicao();
            instituicao.setNome(dto.getNome());
            instituicao.setBairro(dto.getBairro());
            instituicao.setCep(dto.getCep());
            instituicao.setCnpj(dto.getCnpj());
            instituicao.setCidade(dto.getCidade());
            instituicao.setEmail(dto.getEmail());
            instituicao.setLogradouro(dto.getLogradouro());
            instituicao.setNumero(dto.getNumero());
            instituicao.setTelefone(dto.getTelefone());
            instituicao.setTipoLogradouro(dto.getTipoLogradouro());
            instituicao.setUf(dto.getUf());
            instituicao.setAtivo(dto.isAtivo() != null ? dto.isAtivo() : true);
            instituicao.setImagem(fileName != null ? "/uploads/" + fileName : null);
            Instituicao novaInstituicao = instituicaoRepository.save(instituicao);
            return new InstituicaoResponseDTO(novaInstituicao);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar imagem: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar instituição: " + e.getMessage(), e);
        }
    }
    // Atualizar uma instituição por id
    @Override
    public InstituicaoResponseDTO atualizarInstituicao(Integer id, InstituicaoUpdateDTO dto) {
        try {
            Instituicao instituicao = instituicaoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Instituição com id " + id + " não encontrada"));
            if(dto.getNome() != null) {instituicao.setNome(dto.getNome());}
            if(dto.getEmail() != null) {instituicao.setEmail(dto.getEmail());}
            if(dto.getBairro() != null) {instituicao.setBairro(dto.getBairro());}
            if(dto.getCidade() != null) {instituicao.setCidade(dto.getCidade());}
            if(dto.getUf() != null) {instituicao.setUf(dto.getUf());}
            if(dto.getNumero() != null) {instituicao.setNumero(dto.getNumero());}
            if(dto.isAtivo() != null) {instituicao.setAtivo(dto.isAtivo());}
            if(dto.getCnpj() != null) {instituicao.setCnpj(dto.getCnpj());}
            if(dto.getCep() != null) {instituicao.setCep(dto.getCep());}
            if(dto.getLogradouro() != null) {instituicao.setLogradouro(dto.getLogradouro());}
            if(dto.getTipoLogradouro() != null) {instituicao.setTipoLogradouro(dto.getTipoLogradouro());}
            if(dto.getTelefone() != null) {instituicao.setTelefone(dto.getTelefone());}
            if (dto.getImagem() != null && !dto.getImagem().isEmpty()) {
                // Apaga arquivo antigo se existir
                if (instituicao.getImagem() != null) {
                    String caminhoAntigo = instituicao.getImagem();
                    // Remover prefixo /uploads/ para pegar só o nome do arquivo
                    String nomeArquivoAntigo = caminhoAntigo.replace("/uploads/", "");
                    Path caminhoArquivoAntigo = Paths.get(UPLOAD_DIR).resolve(nomeArquivoAntigo).toAbsolutePath();
                    try {
                        Files.deleteIfExists(caminhoArquivoAntigo);
                    } catch (IOException e) {
                        // Logar o erro e continuar (não abortar atualização só por isso)
                        System.err.println("Falha ao deletar arquivo antigo: " + caminhoArquivoAntigo + " - " + e.getMessage());
                    }
                }
                // Salvar nova imagem
                String fileName = salvarImagem(dto.getImagem());
                instituicao.setImagem("/uploads/" + fileName);
            }
            Instituicao instituicaoAtualizada = instituicaoRepository.save(instituicao);
            return new InstituicaoResponseDTO(instituicaoAtualizada);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar imagem: " + e.getMessage(), e);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar instituição: " + e.getMessage(), e);
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
    // Busca instituição por id
    @Override
    public InstituicaoResponseDTO buscarPorId(Integer id) {
        Instituicao instituicao = instituicaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instituicao Com id: " + id + " não encontrado"));
        return new InstituicaoResponseDTO(instituicao);
    }
    // Busca todas as instituições
    @Override
    public List<InstituicaoResponseDTO> listarTodos(){
        List<Instituicao> instituicao = instituicaoRepository.findAll();
        if(instituicao.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma Instituição encontrada");
        }
        return instituicao.stream()
                .map(InstituicaoResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Busca Instituições por nome no campo Search
    public List<InstituicaoResponseDTO> buscarPorNome(String nome){
        List<Instituicao> instituicoes = instituicaoRepository.findByInstituicaoNome(nome);
        if (instituicoes.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma instituição encontrada com o nome " + nome);
        }
        return instituicoes.stream()
                .map(InstituicaoResponseDTO::new)
                .collect(Collectors.toList());
    }
    // Exclui instituição por id
    @Override
    public void excluir(Integer id) {
        if(!instituicaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Instituicao com id: " + id + " não encontrado");
        }
        instituicaoRepository.deleteById(id);
    }
    // Busca instituicao por cnpj
    @Override
    public InstituicaoResponseDTO buscarPorCnpj(String cnpj) {
        Instituicao instituicao = instituicaoRepository.findByCnpj(cnpj)
                .orElseThrow(() ->new  ResourceNotFoundException("Instituição com cnpj " + cnpj + " não encontrada"));
        return new InstituicaoResponseDTO(instituicao);
    }
}

