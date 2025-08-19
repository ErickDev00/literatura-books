package com.example.demo.repository;

import com.example.demo.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query("SELECT l FROM Livro l LEFT JOIN FETCH l.autor")
    List<Livro> findAllWithAutores();


    List<Livro> findByAutorId(Long autorId);

    List<Livro> findByIdiomaIgnoreCase(String idioma);

    @Query("SELECT l FROM Livro l JOIN FETCH l.autor WHERE upper(l.idioma) = upper(:idioma)")
    List<Livro> findByIdiomaWithAutor(@Param("idioma") String idioma);


}
