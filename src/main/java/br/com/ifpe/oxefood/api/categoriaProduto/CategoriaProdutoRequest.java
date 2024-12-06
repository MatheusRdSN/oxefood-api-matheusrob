package br.com.ifpe.oxefood.api.categoriaProduto;

import br.com.ifpe.oxefood.modelo.categoriaProduto.CategoriaProduto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Mesma coisa que getters e setters juntos.
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CategoriaProdutoRequest {
    private String descricao;

    public CategoriaProduto build() {

        return CategoriaProduto.builder()
                .descricao(descricao)
                .build();
    }
}
