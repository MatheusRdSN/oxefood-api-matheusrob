package br.com.ifpe.oxefood.modelo.cliente;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.util.exception.ClienteException;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
    @Autowired // cria instâncias automaticamente
    private ClienteRepository repository;

    @Autowired // cria instâncias automaticamente
    private EnderecoClienteRepository enderecoClienteRepository;

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
    // Verifica se o número de celular começa com "81"
    return foneCelular != null && foneCelular.startsWith("81");
}


 @Transactional
   public EnderecoCliente adicionarEnderecoCliente(Long clienteId, EnderecoCliente endereco) {

       Cliente cliente = this.obterPorID(clienteId);
      
       //Primeiro salva o EnderecoCliente:

       endereco.setCliente(cliente);
       endereco.setHabilitado(Boolean.TRUE);
       enderecoClienteRepository.save(endereco);
      
       //Depois acrescenta o endereço criado ao cliente e atualiza o cliente:

       List<EnderecoCliente> listaEnderecoCliente = cliente.getEnderecos();
      
       if (listaEnderecoCliente == null) {
           listaEnderecoCliente = new ArrayList<EnderecoCliente>();
       }
      
       listaEnderecoCliente.add(endereco);
       cliente.setEnderecos(listaEnderecoCliente);
       repository.save(cliente);
      
       return endereco;
    }

    @Transactional
    public EnderecoCliente atualizarEnderecoCliente(Long id, EnderecoCliente enderecoAlterado) {
 
        EnderecoCliente endereco = enderecoClienteRepository.findById(id).get();
        endereco.setRua(enderecoAlterado.getRua());
        endereco.setNumero(enderecoAlterado.getNumero());
        endereco.setBairro(enderecoAlterado.getBairro());
        endereco.setCep(enderecoAlterado.getCep());
        endereco.setCidade(enderecoAlterado.getCidade());
        endereco.setEstado(enderecoAlterado.getEstado());
        endereco.setComplemento(enderecoAlterado.getComplemento());
 
        return enderecoClienteRepository.save(endereco);
    }

    @Transactional
    public void removerEnderecoCliente(Long idEndereco) {
 
        EnderecoCliente endereco = enderecoClienteRepository.findById(idEndereco).get();
        endereco.setHabilitado(Boolean.FALSE);
        enderecoClienteRepository.save(endereco);
 
        Cliente cliente = this.obterPorID(endereco.getCliente().getId());
        cliente.getEnderecos().remove(endereco);
        repository.save(cliente);
    }
 
 

}
