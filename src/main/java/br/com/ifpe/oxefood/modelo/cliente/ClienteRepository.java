package br.com.ifpe.oxefood.modelo.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

// Interface que estende a interface JpaRepository, que é responsável por realizar as operações de CRUD no banco de dados.
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
