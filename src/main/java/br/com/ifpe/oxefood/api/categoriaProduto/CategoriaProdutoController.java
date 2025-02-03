package br.com.ifpe.oxefood.api.categoriaProduto;

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

import br.com.ifpe.oxefood.modelo.categoriaProduto.CategoriaProduto;
import br.com.ifpe.oxefood.modelo.categoriaProduto.CategoriaProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
    name = "API Categoria de Produto",
    description = "API responsável pelos servidos de Categoria de Produto no sistema"
)
@RestController //determina que essa classe e do tipo Rest
@RequestMapping("/api/categoriaproduto") //DETERMINA A URL para acesar as funçoes essa classe
@CrossOrigin //recber requisiçoes javascript

public class CategoriaProdutoController {
    
 @Autowired
   private CategoriaProdutoService categoriaProdutoService;

    @Operation(
         summary = "Cadastrar Categoria de Produto",
         description = "Realiza o cadastro de uma nova Categoria de Produto"
    )
   @PostMapping //pra acessar essa funçao tem que fazer requisiçoes POST
   public ResponseEntity<CategoriaProduto> save(@RequestBody CategoriaProdutoRequest request) {

       CategoriaProduto categoriaProduto = categoriaProdutoService.save(request.build());
       return new ResponseEntity<CategoriaProduto>(categoriaProduto, HttpStatus.CREATED);
   }

    @Operation(
         summary = "Listar todas as Categorias de Produto",
         description = "Retorna uma lista com todas as Categorias de Produto cadastradas")
       @GetMapping 
    public List<CategoriaProduto> listarTodos() {
        return categoriaProdutoService.listarTodos();
    }

    @Operation(
         summary = "Obter Categoria de Produto por ID",
         description = "Retorna a Categoria de Produto cadastrada com o ID informado"
    )
    @GetMapping("/{id}")
    public CategoriaProduto obterPorID(@PathVariable Long id) {
        return categoriaProdutoService.obterPorID(id);
    }

    @Operation(
         summary = "Atualizar Categoria de Produto",
         description = "Atualiza a Categoria de Produto cadastrada com o ID informado")
     @PutMapping("/{id}") 
 public ResponseEntity<CategoriaProduto> update(@PathVariable("id") Long id, @RequestBody CategoriaProdutoRequest request) {

       categoriaProdutoService.update(id, request.build());
       return ResponseEntity.ok().build();
 }

    @Operation(
         summary = "Deletar Categoria de Produto",
         description = "Deleta a Categoria de Produto cadastrada com o ID informado"
    )
 @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {

       categoriaProdutoService.delete(id);
       return ResponseEntity.ok().build();
   }


}
