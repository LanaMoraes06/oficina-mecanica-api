package com.br.sistemaoficinamecanica.model.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ItemPecaId implements Serializable {

    private UUID ordemServicoId;

    private UUID pecaId;
}
