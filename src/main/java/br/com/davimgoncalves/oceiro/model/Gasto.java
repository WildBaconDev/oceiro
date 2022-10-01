package br.com.davimgoncalves.oceiro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Document("gasto")
public class Gasto {

    @Id
    private String id;
    private String titulo;
    private String descricao;
    private BigDecimal valor;
    private LocalDateTime data;
    private LocalDateTime dataCriacao;
    @DBRef
    private Usuario usuario;
}
