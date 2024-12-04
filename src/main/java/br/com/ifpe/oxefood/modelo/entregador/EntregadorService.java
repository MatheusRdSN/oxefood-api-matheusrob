package br.com.ifpe.oxefood.modelo.entregador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.ifpe.oxefood.util.exception.EntregadorException;
import jakarta.transaction.Transactional;

@Service

public class EntregadorService {
    @Autowired //cria instâncias automaticamente
    private EntregadorRepository repository;
 
    @Transactional //orgazina
    public Entregador save(Entregador entregador) {

        if (!isValidFoneCelular(entregador.getFoneCelular())) {
            throw new EntregadorException(EntregadorException.MSG_PREFIXO_ENTREGADOR);
        }
 
         entregador.setHabilitado(Boolean.TRUE);
        return repository.save(entregador);
    }

        public List<Entregador> listarTodos() {
  
        return repository.findAll();
    }

    public Entregador obterPorID(Long id) {

        return repository.findById(id).get();
    }

    @Transactional
    public void update(Long id, Entregador entregadorAlterado) {

        if (!isValidFoneCelular(entregadorAlterado.getFoneCelular())) {
            throw new EntregadorException(EntregadorException.MSG_PREFIXO_ENTREGADOR);
        }
        // Buscar o entregador no repositório
        Entregador entregador = repository.findById(id).get();
        // Atualizar os atributos do entregador com os valores do entregadorAlterado
        entregador.setNome(entregadorAlterado.getNome());
        entregador.setCpf(entregadorAlterado.getCpf());
        entregador.setRg(entregadorAlterado.getRg());
        entregador.setDataNascimento(entregadorAlterado.getDataNascimento());
        entregador.setFoneCelular(entregadorAlterado.getFoneCelular());
        entregador.setFoneFixo(entregadorAlterado.getFoneFixo());
        entregador.setQtdEntregasRealizadas(entregadorAlterado.getQtdEntregasRealizadas());
        entregador.setValorFrete(entregadorAlterado.getValorFrete());
        entregador.setEnderecoRua(entregadorAlterado.getEnderecoRua());
        entregador.setEnderecoComplemento(entregadorAlterado.getEnderecoComplemento());
        entregador.setEnderecoNumero(entregadorAlterado.getEnderecoNumero());
        entregador.setEnderecoBairro(entregadorAlterado.getEnderecoBairro());
        entregador.setEnderecoCidade(entregadorAlterado.getEnderecoCidade());
        entregador.setEnderecoCep(entregadorAlterado.getEnderecoCep());
        entregador.setEnderecoUf(entregadorAlterado.getEnderecoUf());
        entregador.setAtivo(entregadorAlterado.getAtivo());

        repository.save(entregador);
    }
    
      @Transactional
   public void delete(Long id) {

       Entregador entregador = repository.findById(id).get();
       entregador.setHabilitado(Boolean.FALSE);

       repository.save(entregador);
   }

    // Método auxiliar para verificar se o número de celular é válido
    private boolean isValidFoneCelular(String foneCelular) {
        // Verifica se o número de celular começa com "81" e tem 11 dígitos no total
        return foneCelular != null && foneCelular.matches("^81\\d{9}$");
    }

}
