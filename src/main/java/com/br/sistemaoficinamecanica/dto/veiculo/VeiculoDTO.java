package com.br.sistemaoficinamecanica.dto.veiculo;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
public class VeiculoDTO {


    @NotNull(message = "O placa não pode ser nula")
    private String placa;

    @NotNull(message = "O modelo não pode ser nulo")
    private String modelo;

    @NotNull(message = "O ano da fabricação não pode ser nulo")
    private LocalDate anoFabricacao;

    private List<UUID> clientesIds;

    public Object findAllById(List<UUID> uuids) {
        return null;
    }
}
