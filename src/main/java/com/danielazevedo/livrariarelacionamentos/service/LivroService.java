package com.danielazevedo.livrariarelacionamentos.service;

import org.springframework.stereotype.Service;

import com.danielazevedo.livrariarelacionamentos.exception.EntidadeNaoEncontradaException;
import com.danielazevedo.livrariarelacionamentos.model.Livro;
import com.danielazevedo.livrariarelacionamentos.repository.AutorRepository;
import com.danielazevedo.livrariarelacionamentos.repository.LivroRepository;

@Service
public class LivroService {

	// Injeções de dependência:

	private LivroRepository livroRepository;

	private AutorRepository autorRepository;

	public LivroService(LivroRepository livroRepository, AutorRepository autorRepository) {
		this.livroRepository = livroRepository;
		this.autorRepository = autorRepository;
	}

	// Cadastrar/atualizar livro:
	// O livro virá acompanhado com o idAutor na requisição POST.

	public Livro salvar(Livro livro) {

/*
		Long autorId = livro.getAutor().getId();

		Autor autorDoLivro = autorRepository.findById(autorId).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Não existe cadastro de autor com id", autorId)));

		livro.setAutor(autorDoLivro);

		return livroRepository.save(livro);
*/
		
		livro.setAutor(autorRepository.findById(livro.getAutor().getId()).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não encontrado!"))));
		return livroRepository.save(livro);
		
	}

}
