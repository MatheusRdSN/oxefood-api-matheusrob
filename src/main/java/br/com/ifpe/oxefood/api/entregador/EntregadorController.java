package br.com.ifpe.oxefood.api.entregador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.entregador.Entregador;
import br.com.ifpe.oxefood.modelo.entregador.EntregadorService;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;

@RestController //determina que essa classe e do tipo Rest
@RequestMapping("/api/entregador") //DETERMINA A URL para acesar as funçoes essa classe
@CrossOrigin //recber requisiçoes javascript
@Tag(
    name = "API Entregador",
    description = "API responsável pelos servidos de Entregador no sistema"
)

//DEFINE AS ROTAS 

public class EntregadorController {
    
    @Autowired
   private EntregadorService entregadorService;

    @Operation(
         summary = "Cadastrar Entregador",
         description = "Realiza o cadastro de um novo Entregador"
    )
   @PostMapping //pra acessar essa funçao tem que fazer requisiçoes POST
   public ResponseEntity<Entregador> save(@RequestBody @Valid EntregadorRequest request) {

    Entregador entregador = entregadorService.save(request.build());
       return new ResponseEntity<Entregador>(entregador, HttpStatus.CREATED);

}

@Operation(
         summary = "Listar todos os Entregadores",
         description = "Retorna uma lista com todos os Entregadores cadastrados")
       @GetMapping
    public List<Entregador> listarTodos() {
        return entregadorService.listarTodos();
    }

    @Operation(
         summary = "Obter Entregador por ID",
         description = "Retorna o Entregador cadastrado com o ID informado"
    )
    @GetMapping("/{id}")
    public Entregador obterPorID(@PathVariable Long id) {
        return entregadorService.obterPorID(id);
    }

    @Operation(
         summary = "Atualizar Entregador",
         description = "Realiza a atualização do Entregador cadastrado com o ID informado")
     @PutMapping("/{id}")
 public ResponseEntity<Entregador> update(@PathVariable("id") Long id, @RequestBody EntregadorRequest request) {

       entregadorService.update(id, request.build());
       return ResponseEntity.ok().build();
 }

    @Operation(
         summary = "Deletar Entregador",
         description = "Deleta o Entregador cadastrado com o ID informado"
    )
 @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {

       entregadorService.delete(id);
       return ResponseEntity.ok().build();
   }

}