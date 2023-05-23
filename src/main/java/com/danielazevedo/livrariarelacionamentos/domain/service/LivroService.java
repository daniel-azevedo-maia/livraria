package com.danielazevedo.livrariarelacionamentos.domain.service;


import com.danielazevedo.livrariarelacionamentos.domain.model.Autor;
import com.danielazevedo.livrariarelacionamentos.domain.model.Livro;
import com.danielazevedo.livrariarelacionamentos.domain.exception.EntidadeNaoEncontradaException;
import com.danielazevedo.livrariarelacionamentos.domain.exception.LivroNaoEncontradoException;
import com.danielazevedo.livrariarelacionamentos.domain.repository.LivroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    // Injeções de dependência:

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorService autorService;

    // Cadastrar livro:

    public Livro cadastrarLivro(Livro livro) {

        Autor autorDoLivro = autorService.buscarPorId(livro.getAutor().getId());

        livro.setAutor(autorDoLivro);

        return livroRepository.save(livro);
    }

    // Listar livros:

    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    public LivroService(LivroRepository livroRepository) {

        this.livroRepository = livroRepository;
    }

    // Buscar livro por ID:

    public Livro buscarPorId(Long livroId){
        return livroRepository.findById(livroId).orElseThrow(() ->
                new LivroNaoEncontradoException(livroId));
    }


    // Excluir livro:
    public void excluir(Long livroId) {
        try {
            livroRepository.deleteById(livroId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de livro com o código %d", livroId));
        }
    }

    // Atualizar livro:

    public Livro atualizarLivro(Long livroId, Livro livro) {

        Livro livroAntigo = this.buscarPorId(livroId);
        BeanUtils.copyProperties(livro, livroAntigo, "id", "autor");

        Livro livroAtualizado = this.cadastrarLivro(livroAntigo);
        return livroAtualizado;

    }
}
