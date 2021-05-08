package br.com.zupacademy.izabella.ecommerce.compartilhado.validacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

	@Documented	
	@Target({ ElementType.FIELD })/*Aplicada em atributos*/
	@Retention(RetentionPolicy.RUNTIME) /*Lida em Runtime*/
	@Constraint(validatedBy = UniqueValueValidator.class)

	public @interface UniqueValue {
		
		
		/*Informações Obrigatórias: */
		/*Mensagem default que será carregada quando houver erro de validação*/
	    String message() default "Este dado já consta em nossos registros!";

	    /*Aplicar validação em um grupo*/
	    Class<?>[] groups() default {};
	    
	    
	    /*Enviar informações a mais*/
	    Class<? extends Payload>[] payload() default {};
	    
	    String fieldName();
	    Class<?> domainClass();
	}


