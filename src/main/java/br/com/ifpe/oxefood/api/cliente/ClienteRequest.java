package br.com.ifpe.oxefood.api.cliente;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Mesma coisa que getters e setters juntos.
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

    @NotNull(message = "O Nome é de preenchimento obrigatório")
    @NotEmpty(message = "O Nome é de preenchimento obrigatório")
    @Length(max = 100, message = "O Nome deverá ter no máximo {max} caracteres")
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotBlank(message = "O CPF é de preenchimento obrigatório")
    @CPF
    private String cpf;

    @Pattern(regexp = "^81\\d{11}$", message = "O número de celular deve começar com o prefixo 81 e conter 11 dígitos no total.")
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
