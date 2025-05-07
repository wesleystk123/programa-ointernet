package br.unipar.programacaoweb.library.controller;

import br.unipar.programacaoweb.library.model.Livro;
import br.unipar.programacaoweb.library.service.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Livro>> listarLivros() {
        List<Livro> lista = livroService.listaTodos();
        return lista.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(lista);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        Livro livro = livroService.buscarPorId(id);
        return livro == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(livro);
    }

    @GetMapping("/buscar/por-titulo")
    public ResponseEntity<List<Livro>> buscarPorTitulo(
            @RequestParam String titulo
    ) {
        List<Livro> lista = livroService.buscarPorTitulo(titulo);
        return lista.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(lista);
    }

    @GetMapping("/buscar/por-genero")
    public ResponseEntity<List<Livro>> buscarPorGenero(
            @RequestParam String genero
    ) {
        List<Livro> lista = livroService.buscarPorGenero(genero);
        return lista.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(lista);
    }

    @GetMapping("/buscar/por-genero-paginas")
    public ResponseEntity<List<Livro>> buscarPorGeneroENumeroPaginas(
            @RequestParam String genero,
            @RequestParam Integer paginasMinimas
    ) {
        List<Livro> lista = livroService.buscarPorGeneroENumeroPaginas(genero, paginasMinimas);
        return lista.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(lista);
    }

    @PostMapping("/salvar")
    public ResponseEntity<Livro> salvarLivro(@RequestBody Livro livro) {
        Livro criado = livroService.salvar(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Livro> editarLivro(
            @PathVariable Long id,
            @RequestBody Livro dados
    ) {
        Livro existente = livroService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        existente.setTitulo(dados.getTitulo());
        existente.setNumeroPaginas(dados.getNumeroPaginas());
        existente.setGenero(dados.getGenero());
        existente.setAutor(dados.getAutor());
        Livro atualizado = livroService.salvar(existente);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirLivro(@PathVariable Long id) {
        Livro existente = livroService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        livroService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
