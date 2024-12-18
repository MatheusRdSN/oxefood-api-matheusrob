package br.com.ifpe.oxefood.modelo.cliente;

import java.time.LocalDate;

import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonFormat;

//import br.com.ifpe.oxefood.modelo.acesso.Usuario;
import br.com.ifpe.oxefood.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import java.util.List;


@Entity // Entidade do banco de dados.
@Table(name = "Cliente") // Nome da tabela no banco de dados.
@SQLRestriction("habilitado = true") // Restrição para mostrar apenas os clientes habilitados.
@Builder // Cria um objeto Cliente.
@Getter
@Setter
@AllArgsConstructor // Cria um construtor com todos os atributos.
@NoArgsConstructor // Cria um construtor vazio.
public class Cliente extends EntidadeAuditavel {

   //@OneToOne
   //@JoinColumn(nullable = false)
   //private Usuario usuario;


   @OneToMany(mappedBy = "cliente", orphanRemoval = true, fetch = FetchType.EAGER) // EAGER carrega todos os endereços do cliente e LAZY carrega apenas o cliente sem o endereço.
   //@Fetch(FetchMode.SUBSELECT) // Carrega todos os endereços do cliente.
   private List<EnderecoCliente> enderecos; // Lista de endereços do cliente.

   @Column (nullable = false, length = 100)
   private String nome;

   @Column
   @JsonFormat(pattern = "dd/MM/yyyy")
   private LocalDate dataNascimento;

   @Column (unique = true)
   private String cpf;

   @Column
   private String foneCelular;

   @Column
   private String foneFixo;

}
