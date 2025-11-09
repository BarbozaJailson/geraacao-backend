package br.com.belval.api.geraacao.service;

import br.com.belval.api.geraacao.dto.CampanhaCreateDTO;
import br.com.belval.api.geraacao.dto.CampanhaResponseDTO;
import br.com.belval.api.geraacao.dto.CampanhaUpdateDTO;
import br.com.belval.api.geraacao.model.Campanha;
import br.com.belval.api.geraacao.model.Instituicao;
import java.util.List;

public interface CampanhaService {

    List<CampanhaResponseDTO> findAll();

    List<CampanhaResponseDTO> findByInstituicaoId(Integer instituicaoId);

    CampanhaResponseDTO findById(Integer id);

    CampanhaResponseDTO criarCampanha(CampanhaCreateDTO dto);

    CampanhaResponseDTO update(Integer id, CampanhaUpdateDTO dto);

    void delete(Integer id);
}
