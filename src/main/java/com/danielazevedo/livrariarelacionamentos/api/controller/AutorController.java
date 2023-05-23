package com.danielazevedo.livrariarelacionamentos.api.controller;

import com.danielazevedo.livrariarelacionamentos.domain.model.Autor;
import com.danielazevedo.livrariarelacionamentos.domain.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    // Injeções de dependência:

    private AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    // Listar autores:

    @GetMapping
    public ResponseEntity<List<Autor>> listarAutores() {
        List<Autor> autores = autorService.listarAutores();
        return ResponseEntity.ok(autores);
    }

    // Buscar por ID:
    
    @GetMapping("/{autorId}")
    public ResponseEntity<Autor> buscarPorId(@PathVariable Long autorId) {
        Autor autor = autorService.buscarPorId(autorId);
        return ResponseEntity.ok(autor);
    }

    // Cadastrar autor:
    
    @PostMapping("/novo")
    public ResponseEntity<Autor> cadastrarAutor(@RequestBody Autor autor) {
        Autor autorSalvo = autorService.cadastrarAutor(autor);
        return new ResponseEntity<>(autorSalvo, HttpStatus.CREATED);
    }

    // Atualizar autor:

    @PutMapping("/{autorId}")
    public ResponseEntity<Autor> atualizarAutor(@PathVariable Long autorId,
                                                @RequestBody Autor autor) {

        Autor autorAtualizado = autorService.atualizarAutor(autorId, autor);
        return ResponseEntity.ok(autorAtualizado);
    }
    
    // Deletar por ID:

    @DeleteMapping("/{autorId}")
    public ResponseEntity<?> excluirAutor(@PathVariable Long autorId) {

        autorService.excluir(autorId);
        return ResponseEntity.noContent().build();

    }


}
