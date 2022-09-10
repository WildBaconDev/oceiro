package br.com.davimgoncalves.oceiro.dto;

import org.springframework.http.HttpStatus;

public record ErroDTO(HttpStatus status, String message, String path) {}
