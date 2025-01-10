package br.com.ifpe.oxefood.util.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifpe.oxefood.modelo.acesso.Usuario;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)

public abstract class EntidadeAuditavel extends EntidadeNegocio {
    
   @JsonIgnore // Ignora o campo na serialização
   @Version // Versão da entidade
   private Long versao;

   @JsonIgnore
   @CreatedDate // Data de criação
   private LocalDate dataCriacao;

   @JsonIgnore
   @LastModifiedDate // Data da última modificação
   private LocalDate dataUltimaModificacao;

   @CreatedBy // Usuário que criou a entidade 
    @ManyToOne
    @JoinColumn
   private Usuario criadoPor; // usuário que o criou

   @LastModifiedBy
    @ManyToOne
    @JoinColumn
   private Usuario ultimaModificacaoPor; // usuário que fez a última alteração


}