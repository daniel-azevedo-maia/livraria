package com.danielazevedo.livrariarelacionamentos.api.controller;



import com.danielazevedo.livrariarelacionamentos.domain.model.Livro;
import com.danielazevedo.livrariarelacionamentos.domain.service.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    // Injeções de dependência:

    private LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    // Listar livros:

    @GetMapping
    public ResponseEntity<List<Livro>> listarLivros() {
        List<Livro> livros = livroService.listarLivros();
        return ResponseEntity.ok(livros);
    }

    // Buscar por ID:
    
    @GetMapping("/{livroId}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long livroId) {
        Livro livro = livroService.buscarPorId(livroId);
        return ResponseEntity.ok(livro);
    }

    // Cadastrar livro:
    
    @PostMapping("/novo")
    public ResponseEntity<Livro> cadastrarLivro(@RequestBody Livro livro) {
        Livro livroSalvo = livroService.cadastrarLivro(livro);
        return new ResponseEntity<>(livroSalvo, HttpStatus.CREATED);
    }

    // Atualizar livro:

    @PutMapping("/{livroId}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long livroId,
                                                @RequestBody Livro livro) {

        Livro livroAtualizado = livroService.atualizarLivro(livroId, livro);
        return ResponseEntity.ok(livroAtualizado);
    }
    
    // Deletar por ID:

    @DeleteMapping("/{livroId}")
    public ResponseEntity<?> excluirLivro(@PathVariable Long livroId) {

        livroService.excluir(livroId);
        return ResponseEntity.noContent().build();

    }


}
