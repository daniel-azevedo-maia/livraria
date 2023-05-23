package com.danielazevedo.livrariarelacionamentos.domain.service;

import com.danielazevedo.livrariarelacionamentos.domain.exception.AutorNaoEncontradoException;
import com.danielazevedo.livrariarelacionamentos.domain.exception.EntidadeNaoEncontradaException;
import com.danielazevedo.livrariarelacionamentos.domain.model.Autor;
import com.danielazevedo.livrariarelacionamentos.domain.repository.AutorRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    // Injeções de dependência:

    private AutorRepository autorRepository;

    // Cadastrar autor:

    public Autor cadastrarAutor(Autor autor) {

        return autorRepository.save(autor);
    }

    // Listar autores:

    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    public AutorService(AutorRepository autorRepository) {

        this.autorRepository = autorRepository;
    }

    // Buscar autor por ID:

    public Autor buscarPorId(Long autorId){
        return autorRepository.findById(autorId).orElseThrow(() ->
                new AutorNaoEncontradoException(autorId));
    }

    // Excluir autor:

    public void excluir(Long autorId) {
        try {
            autorRepository.deleteById(autorId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de autor com o código %d", autorId));
        }
    }

    // Atualizar autor:

    public Autor atualizarAutor(Long autorId, Autor autor) {

        Autor autorAntigo = this.buscarPorId(autorId);
        BeanUtils.copyProperties(autor, autorAntigo, "id");

        Autor autorAtualizado = this.cadastrarAutor(autorAntigo);
        return autorAtualizado;

    }
}
