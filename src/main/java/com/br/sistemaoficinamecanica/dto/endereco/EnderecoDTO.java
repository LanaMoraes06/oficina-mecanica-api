package com.br.sistemaoficinamecanica.dto.endereco;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EnderecoDTO {

    @NotNull(message = "O logradouro não pode ser nulo")
    private String logradouro;

    @NotNull(message = "O numero não pode ser nulo")
    @Min(value = 1, message = "O numero não pode ser menor que zero")
    private String numero;

    @NotNull(message = "O bairro não pode ser nulo")
    private String bairro;

    @NotNull(message = "O cidade não pode ser nulo")
    private String cidade;

    @NotNull(message = "O cep não pode ser nulo")
    @Min(value = 1, message = "O cep não pode ser menor que zero")
    private String cep;
}
