package com.br.sistemaoficinamecanica.dto.ordemservico;

import com.br.sistemaoficinamecanica.enums.StatusOS;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class OrdemServicoDTO {


    @NotNull(message = "O status da OS não pode ser nulo")
    private StatusOS status;

    private UUID veiculo;
}
