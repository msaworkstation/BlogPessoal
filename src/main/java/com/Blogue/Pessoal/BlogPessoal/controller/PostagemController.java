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

import com.Blogue.Pessoal.BlogPessoal.model.PostagemModel;
import com.Blogue.Pessoal.BlogPessoal.repository.PostagemRepository;


@RestController
@RequestMapping
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;
	
	@GetMapping
	public ResponseEntity<List<PostagemModel>> getAll(){
		return ResponseEntity.ok(repository.findAll());
		
	}
	 @GetMapping("/{id}")
	 public ResponseEntity<PostagemModel> getById(@PathVariable long id){
		 return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	 }
	
	   @GetMapping("/texto/{texto}")
	   public ResponseEntity<List<PostagemModel>> getByNome(@PathVariable String texto){
		  return ResponseEntity.ok(repository.findAllByTextoContainingIgnoreCase(texto));
	   }
	 
	   @PostMapping
	   public ResponseEntity<PostagemModel> post (@RequestBody PostagemModel texto){
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(texto));
		}
	   
	   @PutMapping
	   public ResponseEntity<PostagemModel> put(@RequestBody PostagemModel texto){
         return repository.findById(texto.getId())
        		 .map(resp -> ResponseEntity.ok().body(repository.save(texto)))
        		 .orElse(ResponseEntity.notFound().build());
 
		   
	   }
	   
	   @DeleteMapping("/{id}")
		public void delete(@PathVariable long id) {
			repository.deleteById(id);
		}
	 
}
