package com.Blogue.Pessoal.BlogPessoal.repository;

import java.util.List;
import java.util.Optional;



import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.Blogue.Pessoal.BlogPessoal.model.UsuarioModel;




@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)// para separa os testes de cada classe
public class UsuarioRepositoryTests {

	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	

	@BeforeAll
	void start() {
		usuarioRepository.save(new UsuarioModel (0L, "Ramon Daniel Santos","ramonzito@clovis.com","1223456789","https://i.imgur.com/FETvs2O.jpg"));
		usuarioRepository.save(new UsuarioModel (0L, "Robson Carmo Santos","robsonbruxinhocarmo@gmail.com","mago123","https://i.imgur.com/FETvs2O.jpg"));
		usuarioRepository.save(new UsuarioModel (0L, "Paola Bracho Santos","ataldaursupadora@yahoo.com","carlosdaniel","https://i.imgur.com/FETvs2O.jpg"));
		usuarioRepository.save(new UsuarioModel (0L, "Sabrina Sanches", "sabrina_sanches@email.com.br", "sabrina123", "https://i.imgur.com/5M2p5Wb.jpg"));
		usuarioRepository.save(new UsuarioModel (0L,"Maria da Silva", "maria_silva@email.com.br", "13465278", "https://i.imgur.com/T12NIp9.jpg" ));
		usuarioRepository.save(new UsuarioModel (0L,"Paulo Antunes", "paulo_antunes@email.com.br", "13465278", "https://i.imgur.com/JR7kUFU.jpg" ));
	}
	
	@Test
	@DisplayName("retorna apenas um usuario")
	public void deveRetornarUmUsuario() {
		Optional <UsuarioModel> usuarioModel = usuarioRepository.findByEmail("ataldaursupadora@yahoo.com");
		assertTrue(usuarioModel.get().getUsuario().equals("ataldaursupadora@yahoo.com"));
	}
	
	@Test
	@DisplayName("Retorna 3 usuarios")
	
	public void deveRetornarTresUsuario() {
		List<UsuarioModel> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("santos");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Ramon Daniel Santos"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Robson Carmo Santos")); 
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Paola Bracho Santos"));
	}
	
	@AfterAll
	
	public void  end() {
		usuarioRepository.deleteAll();
	}
	
}
