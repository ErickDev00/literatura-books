package com.example.demo.repository;

import com.example.demo.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nomeAutor);

    List<Autor> findByAnoFalecimentoAfterOrAnoFalecimentoIsNull(int ano);

}
