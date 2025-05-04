package br.unipar.programacaoweb.library.service;

import br.unipar.programacaoweb.library.model.Livro;
import br.unipar.programacaoweb.library.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    public void excluir(Long id) {
        livroRepository.deleteById(id);
    }

    public Livro buscarPorId(Long id) {
        return livroRepository.findById(id).orElse(null);
    }

    public List<Livro> listaTodos() {
        return livroRepository.findAll();
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Livro> buscarPorGenero(String genero) {
        return livroRepository.findByGeneroContainingIgnoreCase(genero);
    }

    public List<Livro> buscarPorGeneroENumeroPaginas(String genero, Integer paginasMinimas) {
        return livroRepository.findByGeneroAndNumeroPaginasGreaterThanEqual(genero, paginasMinimas);
    }
}
