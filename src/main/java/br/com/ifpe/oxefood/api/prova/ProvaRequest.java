package br.com.ifpe.oxefood.api.prova;

import br.com.ifpe.oxefood.modelo.prova.Prova;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProvaRequest {

    private String texto;

    private double valorUnitario;

    private Integer numero;

    private Integer tempoEntregaMaximo;

    public Prova build() {
        return Prova.builder()
                .texto(texto)
                .valorUnitario(valorUnitario)
                .numero(numero)
                .tempoEntregaMaximo(tempoEntregaMaximo)
                .build();
    }

}
