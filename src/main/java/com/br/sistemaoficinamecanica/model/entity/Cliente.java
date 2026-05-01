package com.br.sistemaoficinamecanica.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @Column(name = "cliente_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "cliente_nome")
    private String nome;

    @Column(name = "cliente_cpf")
    private String cpf;

    @ManyToMany(mappedBy = "clientes")
    @JsonIgnoreProperties("clientes")
    private List<Veiculo> veiculos = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @Column(name = "cliente_ativo")
    private Boolean ativo = true;
}
