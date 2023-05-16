package com.danielazevedo.livrariarelacionamentos.controller;

import com.danielazevedo.livrariarelacionamentos.model.Autor;
import com.danielazevedo.livrariarelacionamentos.service.AutorService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autores")
public class AutorController {

    // Injeções de dependência:
    private AutorRepository autorRepository;

    private AutorService autorService;

    public AutorController(AutorRepository autorRepository, AutorService autorService) {
        this.autorRepository = autorRepository;
        this.autorService = autorService;
    }

    // Listar autores:
    @GetMapping
    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    // Buscar por ID:
    @GetMapping("/{autorId}")
    public ResponseEntity<Autor> buscarPorId(@PathVariable Long autorId) {
        Optional<Autor> autor = autorRepository.findById(autorId);

        if (autor.isPresent()) {
            return ResponseEntity.ok(autor.get());
        }

        return ResponseEntity.notFound().build();
    }

    // Cadastrar autor:
    @PostMapping("/novo")
    @ResponseStatus(HttpStatus.CREATED)
    public Autor cadastrarAutor(@RequestBody Autor autor) {
        return autorService.cadastrarAutor(autor);
    }

    // Atualizar autor:

    @PutMapping("/{autorId}")
    public ResponseEntity<Autor> atualizarAutor(@PathVariable Long autorId,
                                                @RequestBody Autor autor) {

        Optional<Autor> autorAntigo = autorRepository.findById(autorId);

        if(autorAntigo.isPresent()) {
            BeanUtils.copyProperties(autor, autorAntigo.get(), "id");

            Autor autorSalvo = autorService.cadastrarAutor(autorAntigo.get());
            return ResponseEntity.ok(autorSalvo);
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/excluir/{autorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> excluirAutor(@PathVariable Long autorId) {
        try {
            autorService.excluir(autorId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
