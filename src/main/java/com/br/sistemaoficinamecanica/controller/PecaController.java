package com.br.sistemaoficinamecanica.controller;

import com.br.sistemaoficinamecanica.dto.peca.PecaDTO;
import com.br.sistemaoficinamecanica.model.entity.Peca;
import com.br.sistemaoficinamecanica.service.PecaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pecas")
public class PecaController {

    @Autowired
    private PecaService pecaService;

    @PostMapping
    public ResponseEntity<Peca> create(@Valid @RequestBody PecaDTO pecaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pecaService.create(pecaDTO));
    }

    @GetMapping
    public ResponseEntity<List<Peca>> findAll() {
        return ResponseEntity.ok().body(pecaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Peca> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(pecaService.getID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Peca> findById(@PathVariable UUID id, @Valid @RequestBody PecaDTO pecaDTO) {
        return ResponseEntity.ok().body(pecaService.update(id, pecaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Peca> deleteById(@PathVariable UUID id) {
        pecaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
