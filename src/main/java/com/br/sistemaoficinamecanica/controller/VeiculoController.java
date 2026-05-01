package com.br.sistemaoficinamecanica.controller;

import com.br.sistemaoficinamecanica.dto.veiculo.VeiculoDTO;
import com.br.sistemaoficinamecanica.model.entity.Veiculo;
import com.br.sistemaoficinamecanica.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping({"/veiculos"})
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<Veiculo> create(@RequestBody VeiculoDTO veiculo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoService.create(veiculo));
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        veiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Veiculo> getID(@PathVariable UUID id) {
        return ResponseEntity.ok().body(veiculoService.getID(id));
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> getAll() {
        return ResponseEntity.ok().body(veiculoService.getAll());
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Veiculo> update(@PathVariable UUID id, @RequestBody VeiculoDTO veiculoDTO) {
        return ResponseEntity.ok().body(veiculoService.update(id, veiculoDTO));
    }
}


