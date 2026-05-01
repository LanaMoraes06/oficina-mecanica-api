package com.br.sistemaoficinamecanica.controller;


import com.br.sistemaoficinamecanica.dto.ordemservico.OrdemServicoDTO;
import com.br.sistemaoficinamecanica.model.entity.OrdemServico;
import com.br.sistemaoficinamecanica.service.OrdemServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ordemservicos")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @PostMapping
    public ResponseEntity<OrdemServico> create(@Valid @RequestBody OrdemServicoDTO ordemServicoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ordemServicoService.create(ordemServicoDTO));
    }

    @GetMapping
    public ResponseEntity<List<OrdemServico>> findAll() {
        return ResponseEntity.ok().body(ordemServicoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(ordemServicoService.getById(id));
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<OrdemServico> finalizar(@PathVariable UUID id) {
        return ResponseEntity.ok().body(ordemServicoService.finalizar(id));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<OrdemServico> cancelar(@PathVariable UUID id) {
        return ResponseEntity.ok().body(ordemServicoService.cancelar(id));
    }
}
