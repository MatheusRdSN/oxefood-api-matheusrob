package br.com.ifpe.oxefood.api.prova;

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

import br.com.ifpe.oxefood.modelo.prova.Prova;
import br.com.ifpe.oxefood.modelo.prova.ProvaService;

@RestController // determina que essa classe e do tipo Rest
@RequestMapping("/api/prova") // DETERMINA A URL para acesar as funçoes essa classe
@CrossOrigin // recber requisiçoes javascript

public class ProvaController {
    @Autowired
    private ProvaService provaService;

    @PostMapping // pra acessar essa funçao tem que fazer requisiçoes POST
    public ResponseEntity<Prova> save(@RequestBody ProvaRequest request) {

        Prova prova = provaService.save(request.build());
        return new ResponseEntity<Prova>(prova, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Prova> listaProvarTodos() {
        return provaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Prova obterPorID(@PathVariable Long id) {
        return provaService.obterPorID(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prova> update(@PathVariable("id") Long id, @RequestBody ProvaRequest request) {

        provaService.update(id, request.build());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        provaService.delete(id);
        return ResponseEntity.ok().build();
    }

}
