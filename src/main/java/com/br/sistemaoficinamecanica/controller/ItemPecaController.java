package com.br.sistemaoficinamecanica.controller;

import com.br.sistemaoficinamecanica.dto.itemPeca.ItemPecaDTO;
import com.br.sistemaoficinamecanica.model.entity.ItemPeca;
import com.br.sistemaoficinamecanica.service.ItemPecaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/itenspeca")
public class ItemPecaController {

    @Autowired
    private ItemPecaService itemPecaService;

    @PostMapping
    public ResponseEntity<ItemPeca> create(@Valid @RequestBody ItemPecaDTO dto) {
        ItemPeca itemSalvo = itemPecaService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemSalvo);
    }
   
    @PutMapping("/{ordemServicoId}/{pecaId}")
    public ResponseEntity<ItemPeca> update(
            @PathVariable UUID ordemServicoId,
            @PathVariable UUID pecaId,
            @Valid @RequestBody ItemPecaDTO dto) {

        ItemPeca itemAtualizado = itemPecaService.update(ordemServicoId, pecaId, dto);
        return ResponseEntity.ok(itemAtualizado);
    }

    @DeleteMapping("/{ordemServicoId}/{pecaId}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID ordemServicoId,
            @PathVariable UUID pecaId) {

        itemPecaService.delete(ordemServicoId, pecaId);

        return ResponseEntity.noContent().build();
    }
}