package br.com.ifpe.oxefood.modelo.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.util.exception.ClienteException;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
    @Autowired // cria instâncias automaticamente
    private ClienteRepository repository;

    @Transactional // orgazina
    public Cliente save(Cliente cliente) {

        if (!isValidFoneCelular(cliente.getFoneCelular())){
            throw new ClienteException(ClienteException.MSG_PREFIXO_CLIENTE);
        }

        cliente.setHabilitado(Boolean.TRUE);
        return repository.save(cliente);
    }

    public List<Cliente> listarTodos() {
  
        return repository.findAll();
    }

    public Cliente obterPorID(Long id) {

        return repository.findById(id).get();
    }

    @Transactional
    public void update(Long id, Cliente clienteAlterado) {

        if (!isValidFoneCelular(clienteAlterado.getFoneCelular())){
            throw new ClienteException(ClienteException.MSG_PREFIXO_CLIENTE);
        }
 
       Cliente cliente = repository.findById(id).get();
       cliente.setNome(clienteAlterado.getNome());
       cliente.setDataNascimento(clienteAlterado.getDataNascimento());
       cliente.setCpf(clienteAlterado.getCpf());
       cliente.setFoneCelular(clienteAlterado.getFoneCelular());
       cliente.setFoneFixo(clienteAlterado.getFoneFixo());
         
       repository.save(cliente);
   }
 
   @Transactional
   public void delete(Long id) {

       Cliente cliente = repository.findById(id).get();
       cliente.setHabilitado(Boolean.FALSE);

       repository.save(cliente);
   }

   private boolean isValidFoneCelular(String foneCelular) {
    // Verifica se o número de celular começa com "81" e tem 11 dígitos no total
    return foneCelular != null && foneCelular.matches("^81\\d{9}$");
}

}
