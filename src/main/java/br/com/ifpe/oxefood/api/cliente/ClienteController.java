package br.com.ifpe.oxefood.api.cliente; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import br.com.ifpe.oxefood.modelo.cliente.ClienteService;

@RestController //determina que essa classe e do tipo Rest
@RequestMapping("/api/cliente") //DETERMINA A URL para acesar as funçoes essa classe
@CrossOrigin //recber requisiçoes javascript

//DEFINE AS ROTAS PARA CLIENTE
public class ClienteController {  

   @Autowired
   private ClienteService clienteService;

   @PostMapping //pra acessar essa funçao tem que fazer requisiçoes POST
   public ResponseEntity<Cliente> save(@RequestBody ClienteRequest request) {

       Cliente cliente = clienteService.save(request.build());
       return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
   }
}
