package com.br.sistemaoficinamecanica.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "tb_veiculo")
public class Veiculo {

    @Id
    @Column(name = "veiculo_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "veiculo_placa")
    private String placa;

    @Column(name = "veiculo_modelo")
    private String modelo;

    @Column(name = "veiculo_ano_fabricacao")
    private LocalDate anoFabricacao;

    @ManyToMany
    @JoinTable(
            name = "tb_veiculo_cliente",
            joinColumns = @JoinColumn(name = "veiculo_id"),
            inverseJoinColumns = @JoinColumn(name = "cliente_id")
    )
    @JsonIgnoreProperties("veiculos")
    private List<Cliente> clientes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "veiculo")
    private List<OrdemServico> ordemServicos;

    @Column(name = "veiculo_ativo")
    private Boolean ativo = true;


}
