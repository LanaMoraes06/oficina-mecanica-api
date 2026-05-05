package com.br.sistemaoficinamecanica.repository;


import com.br.sistemaoficinamecanica.enums.StatusOS;
import com.br.sistemaoficinamecanica.model.entity.OrdemServico;
import com.br.sistemaoficinamecanica.model.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, UUID> {

    //2.
    Optional<OrdemServico> findFirstByVeiculoOrderByDataAberturaDesc(Veiculo veiculo);

    //5.
    List<OrdemServico> findByStatusIn(List<StatusOS> status);

    //6.
    List<OrdemServico> findAllByOrderByDataAberturaDesc();

    //7.
    List<OrdemServico> findByDataAberturaBetween(LocalDateTime inicio, LocalDateTime fim);

    //12.
    List<OrdemServico> findByValorTotalGreaterThanEqual(BigDecimal valor);

    List<OrdemServico> findByValorTotalGreaterThan(BigDecimal valor);

}
