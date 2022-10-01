package br.com.davimgoncalves.oceiro.controller;

import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseService {

    public String getEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
