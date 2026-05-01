package com.br.sistemaoficinamecanica.model.entity;


import com.br.sistemaoficinamecanica.enums.StatusOS;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_ordemServico")
public class OrdemServico {

    @Id
    @Column(name = "ordemServico_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "ordemServico_dataAbertura")
    private LocalDateTime dataAbertura;

    @Column(name = "ordemServico_valorTotal")
    private BigDecimal valorTotal;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusOS status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;
}
