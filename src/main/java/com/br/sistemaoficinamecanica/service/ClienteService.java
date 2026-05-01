package com.br.sistemaoficinamecanica.service;

import com.br.sistemaoficinamecanica.dto.cliente.ClienteDTO;
import com.br.sistemaoficinamecanica.model.entity.Cliente;
import com.br.sistemaoficinamecanica.model.entity.Endereco;
import com.br.sistemaoficinamecanica.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public Cliente create(ClienteDTO clienteDTO) {
        if (clienteRepository.existsByCpf(clienteDTO.getCpf())) {
            throw new RuntimeException("Erro: Já existe um cliente cadastrado com o CPF " + clienteDTO.getCpf());
        }
        Endereco endereco = new Endereco();
        endereco.setLogradouro(clienteDTO.getLogradouro());
        endereco.setNumero(clienteDTO.getNumero());
        endereco.setBairro(clienteDTO.getBairro());
        endereco.setCidade(clienteDTO.getCidade());
        endereco.setCep(clienteDTO.getCep());

        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEndereco(endereco);
        endereco.setCliente(cliente);
        return clienteRepository.save(cliente);
    }

    public void delete(UUID id) {
        Cliente cliente = clienteRepository
                .findById(id).orElseThrow(() -> new RuntimeException("Veiculo não encontrado"));
        cliente.setAtivo(false);
        clienteRepository.save(cliente);
    }

    public Cliente getID(UUID id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id " + id));
    }


    public List<Cliente> getAll() {
        return clienteRepository.findByAtivoTrue();
    }

    public Cliente update(UUID idCliente, ClienteDTO updateDTO) {
        Cliente clienteExistente = clienteRepository
                .findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return clienteRepository.save(updateCliente(clienteExistente, updateDTO));
    }

    private Cliente updateCliente(Cliente clienteExistente, ClienteDTO updateDTO) {
        clienteExistente.setNome(updateDTO.getNome());
        clienteExistente.setCpf(updateDTO.getCpf());

        if (clienteExistente.getEndereco() != null) {
            clienteExistente.getEndereco().setLogradouro(updateDTO.getLogradouro());
            clienteExistente.getEndereco().setNumero(updateDTO.getNumero());
            clienteExistente.getEndereco().setBairro(updateDTO.getBairro());
            clienteExistente.getEndereco().setCidade(updateDTO.getCidade());
            clienteExistente.getEndereco().setCep(updateDTO.getCep());
        }

        return clienteExistente;
    }
}

