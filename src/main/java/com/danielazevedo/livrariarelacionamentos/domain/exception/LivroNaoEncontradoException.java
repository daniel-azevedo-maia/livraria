package com.danielazevedo.livrariarelacionamentos.domain.exception;

public class LivroNaoEncontradoException extends EntidadeNaoEncontradaException {
    public LivroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public LivroNaoEncontradoException(Long livroId) {
        this(String.format("Não existe um cadastro de livro com o código %d", livroId));
    }
}
