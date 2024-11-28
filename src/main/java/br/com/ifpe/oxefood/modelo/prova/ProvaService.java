package br.com.ifpe.oxefood.modelo.prova;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service

public class ProvaService {
    @Autowired // cria instâncias automaticamente
    private ProvaRepository repository;

    @Transactional // orgazina
    public Prova save(Prova prova) {

        prova.setHabilitado(Boolean.TRUE);
        return repository.save(prova);
    }

    public List<Prova> listarTodos() {

        return repository.findAll();
    }

    public Prova obterPorID(Long id) {

        return repository.findById(id).get();
    }

    @Transactional
    public void update(Long id, Prova provaAlterado) {

        Prova prova = repository.findById(id).get();
        prova.setTexto(provaAlterado.getTexto());
        prova.setValorUnitario(provaAlterado.getValorUnitario());
        prova.setNumero(provaAlterado.getNumero());
        prova.setTempoEntregaMaximo(provaAlterado.getTempoEntregaMaximo());

        repository.save(prova);
    }

    @Transactional
    public void delete(Long id) {

        Prova prova = repository.findById(id).get();
        prova.setHabilitado(Boolean.FALSE);

        repository.save(prova);
    }

}
