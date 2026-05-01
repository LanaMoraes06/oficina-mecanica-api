package com.br.sistemaoficinamecanica.dto.peca;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PecaDTO {


    @NotNull(message = "O nome não pode ser nulo")
    private String nome;

    @NotNull(message = "O fabricante não pode ser nulo")
    private String fabricante;

    @NotNull(message = "O preco não pode ser nulo")
    @Min(value = 0, message = "O preço não pode ser negativo")
    private BigDecimal preco;

    @NotNull(message = "A quantidade do estoque não pode ser nulo")
    @Min(value = 0, message = "O estoque não pode ficar negativo")
    private Integer qtdEstoque;
}
