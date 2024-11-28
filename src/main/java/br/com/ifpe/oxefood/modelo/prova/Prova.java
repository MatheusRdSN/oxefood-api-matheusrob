package br.com.ifpe.oxefood.modelo.prova;

import org.hibernate.annotations.SQLRestriction;
import br.com.ifpe.oxefood.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Prova")
@SQLRestriction("habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Prova extends EntidadeAuditavel {
    @Column
    private String texto;

    @Column
    private double valorUnitario;

    @Column
    private Integer numero;

    @Column
    private Integer tempoEntregaMaximo;

}
