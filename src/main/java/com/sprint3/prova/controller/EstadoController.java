package com.sprint3.prova.controller;

import com.sprint3.prova.modelo.Estado;
import com.sprint3.prova.modelo.Regiao;
import com.sprint3.prova.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping(value = "/api/states")
public class EstadoController {
    @Autowired
    EstadoRepository estadoRepository;

    @GetMapping
    public ResponseEntity<?> listar() {
        List<Estado> estados = estadoRepository.findAll();
        return ResponseEntity.ok(estados);
    }
    @GetMapping(params = "regiao")
    public ResponseEntity<?> listarRegiao(Regiao regiao) {
        List<Estado> estados;
        if (regiao != null) {
            estados = estadoRepository.findByRegiao(regiao);
        } else {
            estados = estadoRepository.findAll();
        }
        return ResponseEntity.ok(estados);
    }
    @GetMapping(params = "filtro")
    public ResponseEntity<?> listarComFiltroPopulacaoOuArea(String filtro) {
        List<Estado> estados;
         if (filtro.equalsIgnoreCase("populacao")) {
            estados = estadoRepository.findAll();
            estados.sort(Comparator.comparing(Estado::getPopulacao).reversed());
        } else if (filtro.equalsIgnoreCase("area")) {
            estados = estadoRepository.findAll();
            estados.sort(Comparator.comparing(Estado::getArea).reversed());
        } else {
            estados = estadoRepository.findAll();
        }
        return ResponseEntity.ok(estados);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Estado> buscaPorId(@PathVariable Long id) {
        Optional<Estado> estado = estadoRepository.findById(id);
        if (estado.isPresent()) {
            return ResponseEntity.ok(estado.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Estado> cadastrar(@RequestBody @Valid Estado estado, UriComponentsBuilder uriComponentsBuilder) {
        estadoRepository.save(estado);
        URI uri = uriComponentsBuilder.path("/api/states/{id}").buildAndExpand(estado.getId()).toUri();
        return ResponseEntity.created(uri).body(estado);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Estado> atualizar(@PathVariable Long id, @RequestBody @Valid Estado estado) {
        Optional<Estado> optional = estadoRepository.findById(id);
        if (optional.isPresent()) {
            Estado _estado = optional.get();
            _estado.setNome(estado.getNome());
            _estado.setCapital(estado.getCapital());
            _estado.setArea(estado.getArea());
            _estado.setPopulacao(estado.getPopulacao());
            _estado.setRegiao(estado.getRegiao());
            return ResponseEntity.ok(estadoRepository.save(_estado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {

        Optional<Estado> optional = estadoRepository.findById(id);
        if (optional.isPresent()) {
            estadoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
