package br.com.ifpe.oxefood.api.entregador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.entregador.Entregador;
import br.com.ifpe.oxefood.modelo.entregador.EntregadorService;

@RestController //determina que essa classe e do tipo Rest
@RequestMapping("/api/entregador") //DETERMINA A URL para acesar as funçoes essa classe
@CrossOrigin //recber requisiçoes javascript

//DEFINE AS ROTAS 

public class EntregadorController {
    
    @Autowired
   private EntregadorService entregadorService;

   @PostMapping //pra acessar essa funçao tem que fazer requisiçoes POST
   public ResponseEntity<Entregador> save(@RequestBody EntregadorRequest request) {

    Entregador entregador = entregadorService.save(request.build());
       return new ResponseEntity<Entregador>(entregador, HttpStatus.CREATED);

}

       @GetMapping
    public List<Entregador> listarTodos() {
        return entregadorService.listarTodos();
    }

    @GetMapping("/{id}")
    public Entregador obterPorID(@PathVariable Long id) {
        return entregadorService.obterPorID(id);
    }

     @PutMapping("/{id}")
 public ResponseEntity<Entregador> update(@PathVariable("id") Long id, @RequestBody EntregadorRequest request) {

       entregadorService.update(id, request.build());
       return ResponseEntity.ok().build();
 }

}