package com.richardeveloper.configs.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.richardeveloper.models.security.LoginForm;
import com.richardeveloper.models.security.TokenDto;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form){		
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			Authentication authentication = authenticationManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			
			return new ResponseEntity<TokenDto>(new TokenDto(token, "Bearer"), HttpStatus.OK);
		}
		catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
			
	}
	
}
