package com.danielazevedo.livrariarelacionamentos.domain.exception;

public class AutorNaoEncontradoException extends EntidadeNaoEncontradaException {
    public AutorNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public AutorNaoEncontradoException(Long autorId) {
        this(String.format("Não existe um cadastro de autor com o código %d", autorId));
    }
}
