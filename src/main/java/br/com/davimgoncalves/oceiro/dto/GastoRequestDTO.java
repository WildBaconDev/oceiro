package br.com.davimgoncalves.oceiro.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GastoRequestDTO(@NotBlank String id, @NotBlank String titulo, @NotBlank String descricao, @NotNull BigDecimal valor, @NotNull LocalDateTime data) { }
