package br.com.ifpe.oxefood.api.cliente;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Mesma coisa que getters e setters juntos.
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

   private String nome;

   @JsonFormat(pattern = "dd/MM/yyyy")
   private LocalDate dataNascimento;

   private String cpf;

   private String foneCelular;

   private String foneFixo;

   public Cliente build() {

       return Cliente.builder()
           .nome(nome)
           .cpf(cpf)
           .dataNascimento(dataNascimento) 
           .foneCelular(foneCelular)
           .foneFixo(foneFixo)
           .build();
   }

}

