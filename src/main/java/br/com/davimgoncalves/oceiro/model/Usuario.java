package br.com.davimgoncalves.oceiro.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("usuario")
public record Usuario(@Id String email, String username, String password, List<Authority> authorities, Boolean enabled) { }
