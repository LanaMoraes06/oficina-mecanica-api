package com.br.sistemaoficinamecanica.repository;


import com.br.sistemaoficinamecanica.model.entity.Peca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PecaRepository extends JpaRepository<Peca, UUID> {
    List<Peca> findByAtivoTrue();

}
