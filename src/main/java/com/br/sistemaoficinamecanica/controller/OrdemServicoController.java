package com.br.sistemaoficinamecanica.controller;


import com.br.sistemaoficinamecanica.dto.ordemservico.OrdemServicoDTO;
import com.br.sistemaoficinamecanica.enums.StatusOS;
import com.br.sistemaoficinamecanica.model.entity.OrdemServico;
import com.br.sistemaoficinamecanica.service.OrdemServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    //2
    @GetMapping("/veiculo/{veiculoId}/ultima")
    public ResponseEntity<OrdemServico> findUltimaOs(@PathVariable UUID veiculoId) {
        return ordemServicoService.getUltimaOsDoVeiculo(veiculoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //5
    @GetMapping("/filtrar-status")
    public ResponseEntity<List<OrdemServico>> findByStatus(@RequestParam List<StatusOS> status) {
        return ResponseEntity.ok().body(ordemServicoService.getStatus(status));
    }

    //6
    @GetMapping("/recentes")
    public ResponseEntity<List<OrdemServico>> findOrderByData() {
        return ResponseEntity.ok().body(ordemServicoService.getDataAberturaDesc());
    }

    //7
    @GetMapping("/periodo")
    public ResponseEntity<List<OrdemServico>> findByPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return ResponseEntity.ok(ordemServicoService.getByDataAbertura(inicio, fim));
    }

    //12
    @GetMapping("/valor-minimo-igual")
    public ResponseEntity<List<OrdemServico>> findByValorMinimoEquals(@RequestParam BigDecimal valor) {
        return ResponseEntity.ok().body(ordemServicoService.getValorTotalGreaterThanEqual(valor));
    }

    //13
    @GetMapping("/valor-minimo")
    public ResponseEntity<List<OrdemServico>> findByValorMinimo(@RequestParam BigDecimal valor) {
        return ResponseEntity.ok().body(ordemServicoService.getValorTotalGreaterThan(valor));
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
