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

    //3
    @GetMapping("/buscar")
    public ResponseEntity<List<Peca>> findNameOrFabricante(
            @RequestParam(required = false, defaultValue = "") String termo) {
        return ResponseEntity.ok().body(pecaService.getByNomeOrFabricante(termo));
    }

    //9
    @GetMapping("/ativos")
    public ResponseEntity<List<Peca>> findByAtivoOrderByNome() {
        return ResponseEntity.ok().body(pecaService.getByAtivoOrderByNome());
    }

    //10
    @GetMapping("/estoque-critico")
    public ResponseEntity<List<Peca>> findByQtdEstoque(@RequestParam(defaultValue = "5") Integer limite) {
        return ResponseEntity.ok().body(pecaService.getByQtdEstoque(limite));
    }

    //11
    @GetMapping("/estoque-baixo")
    public ResponseEntity<List<Peca>> findByQtdEstoqueLessThanEqual(@RequestParam(defaultValue = "5") Integer limite) {
        return ResponseEntity.ok().body(pecaService.getByQtdEstoqueLessThanEqual(limite));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Peca> update(@PathVariable UUID id, @Valid @RequestBody PecaDTO pecaDTO) {
        return ResponseEntity.ok().body(pecaService.update(id, pecaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Peca> deleteById(@PathVariable UUID id) {
        pecaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
