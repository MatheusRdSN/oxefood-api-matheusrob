package br.com.ifpe.oxefood.modelo.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
    @Autowired // cria instâncias automaticamente
    private ClienteRepository repository;

    @Transactional // orgazina
    public Cliente save(Cliente cliente) {

        cliente.setHabilitado(Boolean.TRUE);
        return repository.save(cliente);
    }

}
