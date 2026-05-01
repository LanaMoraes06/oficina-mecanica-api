package com.br.sistemaoficinamecanica.dto.cliente;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ClienteDTO {

    @NotNull(message = "O nome do cliente não pode ser nulo")
    private String nome;


    @NotNull(message = "O CPF do cliente não pode ser nulo")
    private String cpf;

    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String cep;


}
