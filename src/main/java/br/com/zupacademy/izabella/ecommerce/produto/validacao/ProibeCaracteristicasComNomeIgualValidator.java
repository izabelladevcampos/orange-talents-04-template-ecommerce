
package br.com.zupacademy.izabella.ecommerce.produto.validacao;

import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.izabella.ecommerce.produto.NovoProdutoRequest;

public class ProibeCaracteristicasComNomeIgualValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return NovoProdutoRequest.class.isAssignableFrom(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors())
			return;

		NovoProdutoRequest request = (NovoProdutoRequest) target;
		Set<String> nomesIguais = request.buscarCaracteristicasIguais();
		if (!nomesIguais.isEmpty()) {
			errors.rejectValue("caracteristicas", null,
					"A Requisição contém características com nomes iguais: " + nomesIguais);
		}

	}
}
