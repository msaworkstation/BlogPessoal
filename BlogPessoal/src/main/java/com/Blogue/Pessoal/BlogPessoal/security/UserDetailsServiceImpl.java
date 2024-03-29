package com.Blogue.Pessoal.BlogPessoal.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Blogue.Pessoal.BlogPessoal.model.UsuarioModel;
import com.Blogue.Pessoal.BlogPessoal.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {
		Optional<UsuarioModel> usuario = usuarioRepository.findByEmail(email);
		usuario.orElseThrow(() -> new UsernameNotFoundException(email + " não encontrado."));
	 return usuario.map(UserDetailsImpl::new). get();
	}
	
	
}
