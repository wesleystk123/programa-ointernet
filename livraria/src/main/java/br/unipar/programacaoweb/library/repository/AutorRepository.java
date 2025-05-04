package br.unipar.programacaoweb.library.repository;

import br.unipar.programacaoweb.library.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    List<Autor> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.livros")
    List<Autor> findAllWithLivros();
}