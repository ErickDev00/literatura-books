package com.example.demo.model;


import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private Double anoNascimento;

    private Double anoFalecimento;

    @ManyToOne
    @JoinColumn(name = "livro_id") // chave estrangeira para o livro
    private Livro livro;

    public Autor() {}

    public Autor(String nome) {
        this.nome = nome;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Double anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Double getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Double anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }
}
