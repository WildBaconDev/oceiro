package br.com.davimgoncalves.oceiro.exception;

public class UsuarioJaExistenteException extends RuntimeException {

    public UsuarioJaExistenteException() {
        super("Este email já está em uso.");
    }
}
