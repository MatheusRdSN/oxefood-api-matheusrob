package br.com.ifpe.oxefood.modelo.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {
    @Autowired // cria instâncias automaticamente
    private ProdutoRepository repository;

    @Transactional // orgazina
    public Produto save(Produto produto) {

        produto.setHabilitado(Boolean.TRUE);
        return repository.save(produto);
    }

}
