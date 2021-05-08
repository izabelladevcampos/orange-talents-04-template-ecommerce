package br.com.zupacademy.izabella.ecommerce.usuario;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class NovoUsuarioController {

	@Autowired
	private usuarioRepository usuarioRepository;

	@PostMapping
	public ResponseEntity<?> criaUsuario(@RequestBody @Valid NovoUsuarioRequest request) {
		Usuario novoUsuario = request.toModel();
		usuarioRepository.save(novoUsuario);
		return ResponseEntity.ok().build();
	}

}
