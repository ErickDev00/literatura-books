package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutores(
        @JsonAlias("name") String nome,
        @JsonAlias("birth_year") Double anoNascimento,
        @JsonAlias("death_year") Double anoFalecimento
) {
}
