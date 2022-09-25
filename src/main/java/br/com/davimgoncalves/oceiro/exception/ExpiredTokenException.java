package br.com.davimgoncalves.oceiro.exception;

public class ExpiredTokenException extends RuntimeException {
    public ExpiredTokenException() {
        super("Token expirado.");
    }
}
