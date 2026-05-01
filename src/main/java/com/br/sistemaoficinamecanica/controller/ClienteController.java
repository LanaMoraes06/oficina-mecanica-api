package com.br.sistemaoficinamecanica.controller;

import com.br.sistemaoficinamecanica.dto.cliente.ClienteDTO;
import com.br.sistemaoficinamecanica.model.entity.Cliente;
import com.br.sistemaoficinamecanica.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> create(@Valid @RequestBody ClienteDTO cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.create(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getID(@PathVariable UUID id) {
        return ResponseEntity.ok().body(clienteService.getID(id));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        return ResponseEntity.ok().body(clienteService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable UUID id, @Valid @RequestBody ClienteDTO updateDTO) {
        return ResponseEntity.ok().body(clienteService.update(id, updateDTO));
    }
}
