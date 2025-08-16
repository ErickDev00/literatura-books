package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivros(
        @JsonAlias("id") Long id,
        @JsonAlias("name") String titulo,
        @JsonAlias("languages") String idiomas,
        @JsonAlias("download_count") Double numDownloads
) {}
