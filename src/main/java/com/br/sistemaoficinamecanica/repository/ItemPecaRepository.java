package com.br.sistemaoficinamecanica.repository;

import com.br.sistemaoficinamecanica.model.entity.ItemPeca;
import com.br.sistemaoficinamecanica.model.entity.ItemPecaId;
import com.br.sistemaoficinamecanica.model.entity.OrdemServico;
import com.br.sistemaoficinamecanica.model.entity.Peca;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemPecaRepository extends JpaRepository<ItemPeca, ItemPecaId> {
    boolean existsByPeca(Peca peca);


    List<ItemPeca> findByOrdemServicoId(UUID id);


    //8.
    @Transactional
    void deleteByOrdemServico(OrdemServico os);
}
