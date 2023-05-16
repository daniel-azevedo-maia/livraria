package com.danielazevedo.livrariarelacionamentos.service;

import com.danielazevedo.livrariarelacionamentos.exception.EntidadeNaoEncontradaException;
import com.danielazevedo.livrariarelacionamentos.model.Autor;
import com.danielazevedo.livrariarelacionamentos.repository.AutorRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    // Injeções de dependência:

    private AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    // Cadastrar autor:

    public Autor cadastrarAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    // Excluir autor:
    public void excluir(Long autorId) {
        try {
            autorRepository.deleteById(autorId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de autor com o código %d", autorId));
        }
    }



}
