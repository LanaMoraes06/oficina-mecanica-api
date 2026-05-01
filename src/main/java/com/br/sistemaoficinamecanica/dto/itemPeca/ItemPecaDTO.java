package com.br.sistemaoficinamecanica.dto.itemPeca;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ItemPecaDTO {
    @NotNull(message = "O ID da Ordem de Serviço não pode ser nulo.")
    private UUID ordemServicoId;

    @NotNull(message = "O ID da Peça não pode ser nulo.")
    private UUID pecaId;

    @NotNull(message = "A quantidade é obrigatória.")
    @Min(value = 1, message = "A quantidade utilizada deve ser no mínimo 1.")
    private Integer quantidadeUtilizada;
}
