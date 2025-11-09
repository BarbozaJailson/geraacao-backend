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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.belval.api.geraacao.dto.ItemCreateDTO;
import br.com.belval.api.geraacao.dto.ItemResponseDTO;
import br.com.belval.api.geraacao.dto.ItemUpdateDTO;
import br.com.belval.api.geraacao.model.Item;
import br.com.belval.api.geraacao.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemRepository itemRepository;

    private static final String UPLOAD_DIR = "uploads/";

    // Salva um item
    @Override
    @Transactional
    public ItemResponseDTO criarItem(ItemCreateDTO dto) {
        try {
            String fileName = salvarImagem(dto.getImagem());

            Item item = new Item();
            item.setDescricao(dto.getDescricao());
            item.setGenero(dto.getGenero());
            item.setMaterial(dto.getMaterial());
            item.setNome(dto.getNome());
            item.setSecao(dto.getSecao());
            item.setTamanho(dto.getTamanho());
            item.setTipo(dto.getTipo());
            item.setAtivo(dto.isAtivo() != null ? dto.isAtivo() : true);
            item.setImagem(fileName != null ? "/uploads/" + fileName : null);
            item = itemRepository.save(item);
            return new ItemResponseDTO(item);
        }catch(IOException e) {
            throw new RuntimeException("Erro ao salvar imagem: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar item: " + e.getMessage(), e);
        }
    }

    // Atualiza o item por id
    @Override
    @Transactional
    public ItemResponseDTO atualizarItem(Integer id, ItemUpdateDTO dto) {
        try {
            Item item = itemRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Instituição com id " + id + " não encontrada"));
            if(dto.getDescricao() != null) {item.setDescricao(dto.getDescricao());}
            if(dto.getGenero() != null) {item.setGenero(dto.getGenero());}
            if(dto.getMaterial() != null) {item.setMaterial(dto.getMaterial());}
            if(dto.getNome() != null) {item.setNome(dto.getNome());}
            if(dto.getSecao() != null) {item.setSecao(dto.getSecao());}
            if(dto.getTamanho() != null) {item.setTamanho(dto.getTamanho());}
            if(dto.getTipo() != null) {item.setTipo(dto.getTipo());}
            if(dto.isAtivo() != null) {item.setAtivo(dto.isAtivo());}
            if(dto.getImagem() != null && !dto.getImagem().isEmpty()) {
                // Apaga arquivo antigo se existir
                if (item.getImagem() != null) {
                    String caminhoAntigo = item.getImagem();
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
                item.setImagem("/uploads/" + fileName);
            }
            // salva o item
            Item img = itemRepository.save(item);
            return new ItemResponseDTO(img);

        }catch (IOException e) {
            throw new RuntimeException("Erro ao salvar imagem: " + e.getMessage(), e);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar instituição: " + e.getMessage(), e);
        }
    }

    // Salva imagens
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

    // Busca item por id
    @Override
    @Transactional(readOnly = true)
    public ItemResponseDTO buscarPorId(Integer id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item com id " + id + " não encontrado"));
        return new ItemResponseDTO(item);
    }

    // Busca todos os itens
    @Override
    @Transactional(readOnly = true)
    public List<ItemResponseDTO> listarTodos(){
        List<Item> item = itemRepository.findAll();
        if(item.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum item encontrado");
        }
        //lista os itens
        return item.stream()
                .map(ItemResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Exclui item por id
    @Override
    @Transactional
    public void excluir(Integer id) {
        if(!itemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Item com id " + id + " não encontrado");
        }
        itemRepository.deleteById(id);
    }

}

