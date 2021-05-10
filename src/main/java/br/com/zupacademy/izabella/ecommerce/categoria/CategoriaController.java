package br.com.zupacademy.izabella.ecommerce.categoria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@PostMapping
	public ResponseEntity<?> criaCategoria(@RequestBody @Valid NovaCategoriaRequest request) {
		Categoria novaCategoria = request.toModel(manager);
		manager.persist(novaCategoria);
		return ResponseEntity.ok().build();
	}

}
