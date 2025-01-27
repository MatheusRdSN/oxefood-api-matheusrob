package br.com.ifpe.oxefood.api.cliente; 

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

import br.com.ifpe.oxefood.modelo.acesso.UsuarioService;
import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import br.com.ifpe.oxefood.modelo.cliente.ClienteService;
import br.com.ifpe.oxefood.modelo.cliente.EnderecoCliente;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;

@RestController //determina que essa classe e do tipo Rest
@RequestMapping("/api/cliente") //DETERMINA A URL para acesar as funçoes dessa classe
@CrossOrigin //recber requisiçoes javascript
@Tag(
    name = "API Cliente",
    description = "API responsável pelos servidos de cliente no sistema"
)
//DEFINE AS ROTAS PARA CLIENTE
public class ClienteController {  

   @Autowired
   private ClienteService clienteService;

   @Autowired
    private UsuarioService usuarioService;


    @Operation(
          summary = "Cadastrar Cliente",
          description = "Realiza o cadastro de um novo Cliente")
   @PostMapping //pra acessar essa funçao tem que fazer requisiçoes POST
   public ResponseEntity<Cliente> save(@RequestBody @Valid ClienteRequest clienteRequest, HttpServletRequest request) {

       Cliente cliente = clienteService.save(clienteRequest.build(), usuarioService.obterUsuarioLogado(request));
       return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
   }

    @Operation(
             summary = "Listar todos os Clientes",
             description = "Retorna uma lista com todos os Clientes cadastrados")
       @GetMapping
    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
    }

    @Operation(
          summary = "Obter Cliente por ID",
          description = "Retorna o Cliente cadastrado com o ID informado")
    @GetMapping("/{id}")
    public Cliente obterPorID(@PathVariable Long id) {
        return clienteService.obterPorID(id);
    }

    @Operation(
          summary = "Atualizar Cliente",
          description = "Realiza a atualização do Cliente cadastrado com o ID informado")
     @PutMapping("/{id}") 
 public ResponseEntity<Cliente> update(@PathVariable("id") Long id, @RequestBody ClienteRequest clienteRequest, HttpServletRequest request) {

       clienteService.update(id, clienteRequest.build(), usuarioService.obterUsuarioLogado(request));
       return ResponseEntity.ok().build();
 }

    @Operation(
          summary = "Deletar Cliente",
          description = "Deleta o Cliente cadastrado com o ID informado")
 @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {

       clienteService.delete(id);
       return ResponseEntity.ok().build();
   }

    @Operation(
             summary = "Adicionar Endereço ao Cliente",
             description = "Adiciona um novo Endereço ao Cliente cadastrado com o ID informado")
    @PostMapping("/endereco/{clienteId}")
   public ResponseEntity<EnderecoCliente> adicionarEnderecoCliente(@PathVariable("clienteId") Long clienteId, @RequestBody @Valid EnderecoClienteRequest request) {

       EnderecoCliente endereco = clienteService.adicionarEnderecoCliente(clienteId, request.build());
       return new ResponseEntity<EnderecoCliente>(endereco, HttpStatus.CREATED);
   }

    @Operation(
          summary = "Atualizar Endereço do Cliente",
          description = "Atualiza o Endereço do Cliente cadastrado com o ID informado")
   @PutMapping("/endereco/{enderecoId}")
   public ResponseEntity<EnderecoCliente> atualizarEnderecoCliente(@PathVariable("enderecoId") Long enderecoId, @RequestBody EnderecoClienteRequest request) {

       EnderecoCliente endereco = clienteService.atualizarEnderecoCliente(enderecoId, request.build());
       return new ResponseEntity<EnderecoCliente>(endereco, HttpStatus.OK);
   }
  
    @Operation(
          summary = "Deletar Endereço do Cliente",
          description = "Deleta o Endereço do Cliente cadastrado com o ID informado")
   @DeleteMapping("/endereco/{enderecoId}")
   public ResponseEntity<Void> removerEnderecoCliente(@PathVariable("enderecoId") Long enderecoId) {

       clienteService.removerEnderecoCliente(enderecoId);
       return ResponseEntity.noContent().build();
   }




}
