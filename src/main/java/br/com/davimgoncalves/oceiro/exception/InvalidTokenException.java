package br.com.davimgoncalves.oceiro.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("Invalid token.");
    }
}
