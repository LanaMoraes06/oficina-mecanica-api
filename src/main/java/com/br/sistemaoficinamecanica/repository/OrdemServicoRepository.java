package com.br.sistemaoficinamecanica.repository;


import com.br.sistemaoficinamecanica.model.entity.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, UUID> {
}
