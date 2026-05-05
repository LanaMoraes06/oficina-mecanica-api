package com.br.sistemaoficinamecanica.repository;

import com.br.sistemaoficinamecanica.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    boolean existsByCpf(String cpf);

    //1
    List<Cliente> findByAtivoTrue();

    //2
    List<Cliente> findByNomeContainingOrCpfContaining(String nome, String cpf);


}
