package com.example.demo.model;

import com.example.demo.dto.LivroDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivros(
        @JsonAlias("count") Integer total,
        @JsonAlias("next") String proximaPagina,
        @JsonAlias("previous") String paginaAnterior,
        @JsonAlias("results") List<LivroDTO> resultados
) {}
