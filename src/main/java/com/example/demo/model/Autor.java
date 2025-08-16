package com.example.demo.model;


import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {

    @Column(unique = true)
    private String nome;

    private Double anoNascimento;

    private Double anoFalecimento;




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
