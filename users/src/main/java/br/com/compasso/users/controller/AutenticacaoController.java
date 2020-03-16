package br.com.compasso.users.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.users.config.security.TokenService;
import br.com.compasso.users.controller.dto.TokenDto;
import br.com.compasso.users.controller.form.LoginForm;
import br.com.compasso.users.model.Usuario;
import br.com.compasso.users.repository.UsuarioRepository;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository userRepo;
	
	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken dadosLogin = form.convert();
		
		try {
			Authentication authenticate = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authenticate);
			
			Optional<Usuario> usuario = userRepo.findByLogin(form.getLogin());
			if(usuario.isPresent()) {
				Usuario userCast = usuario.get();
				return ResponseEntity.ok(new TokenDto(token, "Bearer", userCast.getId()));
			} else {
				return ResponseEntity.badRequest().build();
			}
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	
}
