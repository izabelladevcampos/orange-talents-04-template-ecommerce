package br.com.zupacademy.izabella.ecommerce.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface usuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByLogin(String login);
}
