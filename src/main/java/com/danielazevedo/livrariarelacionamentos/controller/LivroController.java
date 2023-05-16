package com.danielazevedo.livrariarelacionamentos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielazevedo.livrariarelacionamentos.exception.EntidadeNaoEncontradaException;
import com.danielazevedo.livrariarelacionamentos.model.Livro;
import com.danielazevedo.livrariarelacionamentos.repository.LivroRepository;
import com.danielazevedo.livrariarelacionamentos.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {

	// Injeções de dependência:

	private LivroRepository livroRepository;

	private LivroService livroService;

	public LivroController(LivroRepository livroRepository, LivroService livroService) {
		this.livroRepository = livroRepository;
		this.livroService = livroService;
	}

	// Listar todos:

	@GetMapping
	public List<Livro> listarTodos() {
		return livroRepository.findAll();
	}

	// Buscar por ID:

	@GetMapping("/{livroId}")
	public ResponseEntity<Livro> buscarPorId(@PathVariable Long livroId) {

		Optional<Livro> livro = livroRepository.findById(livroId);
		
		if (livro.isPresent()) {
			return ResponseEntity.ok(livro.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	// Cadastrar livro:
	
	@PostMapping("/novo")
	public ResponseEntity<?> cadastrarAutor(@RequestBody Livro livro) {
		
		try {
			livro = livroService.salvar(livro);
			return ResponseEntity.status(HttpStatus.CREATED).body(livro);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
