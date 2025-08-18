package com.example.demo.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String titulo;

    @Column(length = 10)
    private String idioma;

    private Double numDownloads;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "livro")
    private List<Autor> autores;


    // Construtor padrão
    public Livro() {}


    public List<Autor> getAutor() {
        return autores;
    }

    public void setAutor(List<Autor> autores) {
        this.autores = autores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumDownloads() {
        return numDownloads;
    }

    public void setNumDownloads(Double numDownloads) {
        this.numDownloads = numDownloads;
    }
}
