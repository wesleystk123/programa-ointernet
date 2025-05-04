package br.unipar.programacaoweb.library.controller;

import br.unipar.programacaoweb.library.model.Autor;
import br.unipar.programacaoweb.library.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autor")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Autor>> listar() {
        List<Autor> ListaAutores = autorService.listaTodos();
        if (ListaAutores.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(ListaAutores);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Autor> buscarPorId(@PathVariable Long id) {
        Autor autor = autorService.buscarPorId(id);
        if (autor == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(autor);
    }

    @PostMapping("/salvar")
    public ResponseEntity<Autor> salvar(@RequestBody Autor autor) {
        Autor autorCriado = autorService.salvar(autor);
        return ResponseEntity.status(201).body(autorCriado);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Autor> editar(@PathVariable Long id, @RequestBody Autor autor) {
        Autor autorAtualizado = autorService.editar(id, autor);
        if (autorAtualizado == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(autorAtualizado);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean remover = autorService.excluir(id);
        if (!remover)
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }


}