package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDTO(
        Long id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<AutorDTO> autores,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Integer numDownloads,
        @JsonAlias("summaries") List<String> resumos
) {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Título: ").append(titulo).append("\n");

        if (autores != null && !autores.isEmpty()) {
            sb.append("Autores: ");
            for (int i = 0; i < autores.size(); i++) {
                AutorDTO autor = autores.get(i);
                sb.append(autor.nome());
                if (i < autores.size() - 1) sb.append(", ");
            }
            sb.append("\n");
        }

        if (idiomas != null && !idiomas.isEmpty()) {
            sb.append("Idiomas: ").append(String.join(", ", idiomas)).append("\n");
        }

        sb.append("Downloads: ").append(numDownloads).append("\n");

        if (resumos != null && !resumos.isEmpty()) {
            sb.append("Resumo: ").append(resumos.get(0)).append("\n");
        }

        return sb.toString();
    }
}
