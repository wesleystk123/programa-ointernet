package br.unipar.programacaoweb.library.repository;

import br.unipar.programacaoweb.library.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByGeneroContainingIgnoreCase(String genero);

    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    @Query("SELECT l FROM Livro l WHERE LOWER(l.genero) = LOWER(:genero) AND l.numeroPaginas >= :numeroPaginas")
    List<Livro> findByGeneroAndNumeroPaginasGreaterThanEqual(
            @Param("genero") String genero,
            @Param("numeroPaginas") Integer numeroPaginas
    );

}
