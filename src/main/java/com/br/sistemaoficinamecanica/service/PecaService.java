package com.br.sistemaoficinamecanica.service;


import com.br.sistemaoficinamecanica.dto.peca.PecaDTO;
import com.br.sistemaoficinamecanica.model.entity.Peca;
import com.br.sistemaoficinamecanica.repository.ItemPecaRepository;
import com.br.sistemaoficinamecanica.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PecaService {

    @Autowired
    private PecaRepository pecaRepository;

    @Autowired
    private ItemPecaRepository itemPecaRepository;

    public Peca create(PecaDTO pecaDTO) {
        Peca peca = new Peca();
        peca.setNome(pecaDTO.getNome());
        peca.setFabricante(pecaDTO.getFabricante());
        peca.setPreco(pecaDTO.getPreco());
        peca.setQtdEstoque(pecaDTO.getQtdEstoque());

        return pecaRepository.save(peca);
    }

    public void delete(UUID id) {
        Peca pecaExistente = pecaRepository
                .findById(id).orElseThrow(() -> new RuntimeException("Peca não encontrada com id " + id));

        boolean pecaJaFoiUsada = itemPecaRepository.existsByPeca(pecaExistente);
        if (pecaJaFoiUsada) {
            pecaExistente.setAtivo(false);
            pecaExistente.setQtdEstoque(0);
            pecaRepository.save(pecaExistente);
        } else {
            pecaRepository.delete(pecaExistente);
        }
    }


    public Peca getID(UUID id) {
        return pecaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Peca não encontrada com id " + id));
    }

    public List<Peca> getAll() {
        return pecaRepository.findByAtivoTrue();
    }

    //3
    public List<Peca> getByNomeOrFabricante(String termo) {
        return pecaRepository.findByNomeContainingIgnoreCaseOrFabricanteContainingIgnoreCase(termo, termo);
    }

    //9
    public List<Peca> getByAtivoOrderByNome() {
        return pecaRepository.findByAtivoTrueOrderByNome();
    }

    //10
    public List<Peca> getByQtdEstoque(Integer limite) {
        return pecaRepository.findByQtdEstoqueLessThan(limite);
    }

    //11
    public List<Peca> getByQtdEstoqueLessThanEqual(Integer limite) {
        return pecaRepository.findByQtdEstoqueLessThanEqual(limite);
    }


    public Peca update(UUID id, PecaDTO pecaDTO) {
        Peca pecaExistente = pecaRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Peca não encontrada com id " + id));

        return pecaRepository.save(updatePeca(pecaExistente, pecaDTO));
    }

    private Peca updatePeca(Peca pecaExistente, PecaDTO updateDTO) {
        pecaExistente.setNome(updateDTO.getNome());
        pecaExistente.setFabricante(updateDTO.getFabricante());
        pecaExistente.setPreco(updateDTO.getPreco());
        pecaExistente.setQtdEstoque(updateDTO.getQtdEstoque());
        return pecaExistente;
    }
}
