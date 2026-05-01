package com.br.sistemaoficinamecanica.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_item_peca")
public class ItemPeca {

    @EmbeddedId
    private ItemPecaId id;

    @JsonIgnore
    @ManyToOne
    @MapsId("ordemServicoId")
    @JoinColumn(name = "ordem_servico_id")
    private OrdemServico ordemServico;

    @JsonIgnore
    @ManyToOne
    @MapsId("pecaId")
    @JoinColumn(name = "peca_id")
    private Peca peca;

    @Column(name = "qtdUtilizada")
    private Integer quantidadeUtilizada;
}
