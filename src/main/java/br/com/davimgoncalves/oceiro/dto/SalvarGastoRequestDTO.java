package br.com.davimgoncalves.oceiro.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SalvarGastoRequestDTO(@NotBlank String titulo, String descricao, @NotNull BigDecimal valor,
                                    @NotNull LocalDateTime data) { }
