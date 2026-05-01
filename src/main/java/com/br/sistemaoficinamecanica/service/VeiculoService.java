package com.br.sistemaoficinamecanica.service;


import com.br.sistemaoficinamecanica.dto.veiculo.VeiculoDTO;
import com.br.sistemaoficinamecanica.model.entity.Cliente;
import com.br.sistemaoficinamecanica.model.entity.Veiculo;
import com.br.sistemaoficinamecanica.repository.ClienteRepository;
import com.br.sistemaoficinamecanica.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Veiculo create(VeiculoDTO veiculoDTO) {
        if (veiculoRepository.existsByPlaca(veiculoDTO.getPlaca())) {
            throw new RuntimeException("Erro: Já existe um veiculo cadastrado com a placa " + veiculoDTO.getPlaca());
        }
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(veiculoDTO.getPlaca());
        veiculo.setModelo(veiculoDTO.getModelo());
        veiculo.setAnoFabricacao(veiculoDTO.getAnoFabricacao());
        veiculo.setAtivo(true);

        Optional.ofNullable(veiculoDTO.getClientesIds())
                .filter(ids -> !ids.isEmpty())
                .ifPresent(ids -> {
                    List<Cliente> clientesEncontrados = clienteRepository.findAllById(ids);
                    if (clientesEncontrados.isEmpty()) {
                        throw new RuntimeException("Erro: Nenhum cliente encontrado com os IDs informados.");
                    }
                    veiculo.setClientes(clientesEncontrados);
                    clientesEncontrados.forEach(cliente -> cliente.getVeiculos().add(veiculo));
                });
        return veiculoRepository.save(veiculo);

    }

    public void delete(UUID id) {
        Veiculo veiculoExistente = veiculoRepository
                .findById(id).orElseThrow(() -> new RuntimeException("Veiculo não encontrado"));
        veiculoRepository.delete(veiculoExistente);
    }

    public Veiculo getID(UUID id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id " + id));
    }

    public List<Veiculo> getAll() {
        return veiculoRepository.findAll();
    }


    public Veiculo update(UUID idVeiculo, VeiculoDTO updateDTO) {
        Veiculo veiculoExistente = veiculoRepository
                .findById(idVeiculo)
                .orElseThrow(() -> new RuntimeException("Veiculo não encontrado"));

        return veiculoRepository.save(updateVeiculo(veiculoExistente, updateDTO));
    }

    private Veiculo updateVeiculo(Veiculo veiculoExistente, VeiculoDTO updateDTO) {
        veiculoExistente.setPlaca(updateDTO.getPlaca());
        veiculoExistente.setModelo(updateDTO.getModelo());
        veiculoExistente.setAnoFabricacao(updateDTO.getAnoFabricacao());
        return veiculoExistente;
    }
}


