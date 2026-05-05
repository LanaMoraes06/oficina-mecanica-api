package com.br.sistemaoficinamecanica.repository;


import com.br.sistemaoficinamecanica.model.entity.Peca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PecaRepository extends JpaRepository<Peca, UUID> {

    //1
    List<Peca> findByAtivoTrue();

    //3
    List<Peca> findByNomeContainingIgnoreCaseOrFabricanteContainingIgnoreCase(String nome, String fabricante);

    //9
    List<Peca> findByAtivoTrueOrderByNome();

    //10
    List<Peca> findByQtdEstoqueLessThan(Integer limite);

    List<Peca> findByQtdEstoqueLessThanEqual(Integer limite);

}
