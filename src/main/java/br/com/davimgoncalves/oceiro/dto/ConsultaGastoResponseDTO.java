package br.com.davimgoncalves.oceiro.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ConsultaGastoResponseDTO(String id, String titulo, String descricao, BigDecimal valor, LocalDateTime data) { }
