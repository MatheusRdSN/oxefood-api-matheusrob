package br.com.ifpe.oxefood.modelo.entregador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service

public class EntregadorService {
    @Autowired //cria instâncias automaticamente
    private EntregadorRepository repository;
 
    @Transactional //orgazina
    public Entregador save(Entregador entregador) {
 
         entregador.setHabilitado(Boolean.TRUE);
        return repository.save(entregador);
    }

        public List<Entregador> listarTodos() {
  
        return repository.findAll();
    }

    public Entregador obterPorID(Long id) {

        return repository.findById(id).get();
    }

}
