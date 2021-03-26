package com.richardeveloper.configs.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.richardeveloper.models.security.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String email);
	
}
