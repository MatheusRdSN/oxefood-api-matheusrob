package br.com.ifpe.oxefood.modelo.cliente;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifpe.oxefood.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity // Entidade do banco de dados.
@Table(name = "EnderecoCliente") // Nome da tabela no banco de dados.
@SQLRestriction("habilitado = true") // Restrição para mostrar apenas os clientes habilitados.
@Builder // Cria um objeto Cliente.
@Getter
@Setter
@AllArgsConstructor // Cria um construtor com todos os atributos.
@NoArgsConstructor // Cria um construtor vazio.
public class EnderecoCliente extends EntidadeAuditavel {

    
   @JsonIgnore // Ignora o cliente na serialização do JSON, evitando um loop infinito.
   @ManyToOne // Muitos endereços para um cliente.
   private Cliente cliente;

   @Column // Cria uma coluna no banco de dados.
   private String rua;

   @Column
   private String numero;

   @Column
   private String bairro;

   @Column
   private String cep;

   @Column
   private String cidade;

   @Column
   private String estado;

   @Column
   private String complemento;


    
}
