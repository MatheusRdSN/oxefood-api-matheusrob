package br.com.ifpe.oxefood.api.cliente;

import java.time.LocalDate;
import java.util.Arrays;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.acesso.Perfil;
import br.com.ifpe.oxefood.modelo.acesso.Usuario;
import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data // Mesma coisa que getters e setters juntos.
@Builder // Cria um objeto ClienteRequest.
@NoArgsConstructor 
@AllArgsConstructor
public class ClienteRequest {

    @NotBlank(message = "O e-mail é de preenchimento obrigatório")
    @Email
    private String email;

    @NotBlank(message = "A senha é de preenchimento obrigatório")
    private String password;


    @NotNull(message = "O Nome é de preenchimento obrigatório")
    @NotEmpty(message = "O Nome é de preenchimento obrigatório")
    @Length(max = 100, message = "O Nome deverá ter no máximo {max} caracteres")
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotBlank(message = "O CPF é de preenchimento obrigatório")
    @CPF
    private String cpf;

    private String foneCelular;

    private String foneFixo;

    @AssertTrue(message = "O número de celular deve começar com o prefixo 81.")
    public boolean isFoneCelularValido() {
        // Verifica se o número de celular não é nulo e começa com "81"
        return foneCelular != null && foneCelular.startsWith("(81");
    }

    // Método que cria um objeto Usuario.
    public Usuario buildUsuario() {
       return Usuario.builder()
           .username(email)
           .password(password)
           .roles(Arrays.asList(new Perfil(Perfil.ROLE_CLIENTE)))
           .build();
    }   


    public Cliente build() {

        return Cliente.builder()
                .usuario(buildUsuario())
                .nome(nome)
                .cpf(cpf)
                .dataNascimento(dataNascimento)
                .foneCelular(foneCelular)
                .foneFixo(foneFixo)
                .build();
    }
    

}
