package com.Blogue.Pessoal.BlogPessoal.controller;

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

import com.Blogue.Pessoal.BlogPessoal.model.TemaModel;
import com.Blogue.Pessoal.BlogPessoal.repository.TemaRepository;


@RestController
@RequestMapping("/tema")
@CrossOrigin(origins = "* ", allowedHeaders = "*")
public class TemaController {

	
	@Autowired
	private TemaRepository repository; // o ProdutoRepository virou o repository (renomiou)
	
	@GetMapping
	public ResponseEntity<List<TemaModel>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TemaModel> getByEntity(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<TemaModel>> getBydescricaoEntity(@PathVariable String descricao){
    	return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
    	
    }
    @PostMapping
     public ResponseEntity<TemaModel> post (@RequestBody TemaModel descricao){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(descricao));
	}
    @PutMapping
    public ResponseEntity<TemaModel> put(@RequestBody TemaModel descricao){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(descricao));
	   
   }
    @DeleteMapping
    public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
    
}


