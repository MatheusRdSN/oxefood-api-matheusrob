package br.com.ifpe.oxefood.api.produto;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.categoriaProduto.CategoriaProdutoService;
import br.com.ifpe.oxefood.modelo.produto.Produto;
import br.com.ifpe.oxefood.modelo.produto.ProdutoService;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
    name = "API Produto",
    description = "API responsável pelos servidos de Produto no sistema"
)
@RestController // determina que essa classe e do tipo Rest
@RequestMapping("/api/produto") // DETERMINA A URL para acesar as funçoes essa classe
@CrossOrigin // recber requisiçoes javascript

public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

      @Autowired
   private CategoriaProdutoService categoriaProdutoService;


    @Operation(
        summary = "Cadastrar Produto",
        description = "Realiza o cadastro de um novo Produto"
    )
    @PostMapping // pra acessar essa funçao tem que fazer requisiçoes POST
    public ResponseEntity<Produto> save(@RequestBody @Valid ProdutoRequest request) {

        Produto produtoNovo = request.build();
        produtoNovo.setCategoria(categoriaProdutoService.obterPorID(request.getIdCategoria()));
        Produto produto = produtoService.save(produtoNovo);
 
        return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
        
        
    }


    @Operation(
        summary = "Listar todos os Produtos",
        description = "Retorna uma lista com todos os Produtos cadastrados"
    )
    @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    @Operation(
        summary = "Obter Produto por ID",
        description = "Retorna o Produto cadastrado com o ID informado"
    )
    @GetMapping("/{id}")
    public Produto obterPorID(@PathVariable Long id) {
        return produtoService.obterPorID(id);
    }


    @Operation(
        summary = "Atualizar Produto",
        description = "Realiza a atualização do Produto cadastrado com o ID informado")
     @PutMapping("/{id}")
 public ResponseEntity<Produto> update(@PathVariable("id") Long id, @RequestBody ProdutoRequest request) {

    Produto produto = request.build();
    produto.setCategoria(categoriaProdutoService.obterPorID(request.getIdCategoria()));
    produtoService.update(id, produto);
       return ResponseEntity.ok().build();
 }



    @Operation(
        summary = "Deletar Produto",
        description = "Deleta o Produto cadastrado com o ID informado"
    )
@DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {

    
     produtoService.delete(id);
       return ResponseEntity.ok().build();
   }


    @Operation(
         summary = "Filtrar Produtos",
         description = "Retorna uma lista de Produtos filtrados pelos parâmetros informados")
     @PostMapping("/filtrar")
   public List<Produto> filtrar(
           @RequestParam(value = "codigo", required = false) String codigo,
           @RequestParam(value = "titulo", required = false) String titulo,
           @RequestParam(value = "idCategoria", required = false) Long idCategoria) {

       return produtoService.filtrar(codigo, titulo, idCategoria);
   }

    
}
