package br.com.ifpe.oxefood.api.cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import br.com.ifpe.oxefood.modelo.cliente.EnderecoCliente;

@Data // Mesma coisa que getters e setters juntos.
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoClienteRequest {

   private String rua;

   private String numero;

   private String bairro;

   private String cep;

   private String cidade;

   private String estado;

   private String complemento;

   public EnderecoCliente build() {

       return EnderecoCliente.builder()
               .rua(rua)
               .numero(numero)
               .bairro(bairro)
               .cep(cep)
               .cidade(cidade)
               .estado(estado)
               .complemento(complemento)
               .build();
   }


}
