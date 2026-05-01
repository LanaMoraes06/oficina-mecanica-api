package com.br.sistemaoficinamecanica.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "tb_peca")
public class Peca {

    @Id
    @Column(name = "peca_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "peca_nome")
    private String nome;

    @Column(name = "peca_fabricante")
    private String fabricante;

    @Column(name = "peca_preco")
    private BigDecimal preco;

    @Column(name = "peca_qtdEstoque")
    private Integer qtdEstoque;

    @Column(name = "peca_ativo")
    private Boolean ativo = true;
}
