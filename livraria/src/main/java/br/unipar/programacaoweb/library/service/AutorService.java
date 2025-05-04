package br.unipar.programacaoweb.library.service;

import br.unipar.programacaoweb.library.model.Autor;
import br.unipar.programacaoweb.library.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> listaTodos() {
        return autorRepository.findAll();
    }

    public Autor buscarPorId(Long id) {
        return autorRepository.findById(id).orElse(null);
    }

    public Autor salvar(Autor autor) {
        return autorRepository.save(autor);
    }

    public Autor editar(Long id, Autor dados) {
        Autor existente = buscarPorId(id);
        if (existente == null) return null;
        existente.setNome(dados.getNome());
        existente.setNacionalidade(dados.getNacionalidade());
        existente.setDataNascimento(dados.getDataNascimento());
        existente.setEmail(dados.getEmail());
        return autorRepository.save(existente);
    }

    public boolean excluir(Long id) {
        if (!autorRepository.existsById(id)) {
            return false;
        }
        autorRepository.deleteById(id);
        return true;
    }

    public List<Autor> listarTodosComLivros() {
        return autorRepository.findAllWithLivros();
    }
}