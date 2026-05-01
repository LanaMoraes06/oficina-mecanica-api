package com.br.sistemaoficinamecanica.repository;


import com.br.sistemaoficinamecanica.model.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {
    boolean existsByPlaca(String placa);

    List<Veiculo> findByAtivoTrue();

}
