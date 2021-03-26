package com.richardeveloper.configs.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.richardeveloper.models.security.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${estudo.jwt.expiration}")
	private String expiration;

	@Value("${estudo.jwt.secret}")
	private String secret;
	
	public String gerarToken(Authentication authentication) {
		
		Usuario usuario = (Usuario) authentication.getPrincipal();
		Date momento = new Date();
		Date dataExpiracao = new Date(momento.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
					.setIssuer("Registro Estudo API")
					.setSubject(usuario.getId().toString())
					.setIssuedAt(momento)
					.setExpiration(dataExpiracao)
					.signWith(SignatureAlgorithm.HS256, secret)
					.compact();
	}

	public boolean validacao(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		}
		catch (Exception e) {
			return false;			
		}
	}

	public Long getIdUsuario(String token) {
		Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();		
		return Long.parseLong(body.getSubject());
		
	}
	
	

}
