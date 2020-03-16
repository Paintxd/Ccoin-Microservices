package br.com.compasso.users.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.compasso.users.model.Usuario;
import br.com.compasso.users.repository.UsuarioRepository;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	
	private UsuarioRepository repo;
	
	public AutenticacaoTokenFilter(TokenService tokenService, UsuarioRepository userRepository) {
		this.tokenService = tokenService;
		this.repo = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperaToken(request);
		Boolean valido = tokenService.isValid(token);
		
		if(valido) {
			autenticaCliente(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void autenticaCliente(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		Usuario usuario = repo.findById(idUsuario).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}

	private String recuperaToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token==null || token.isEmpty() || token.startsWith("Barrer ")) {
			return null;
		} 
		
		return token.substring(7, token.length());
	}

}
