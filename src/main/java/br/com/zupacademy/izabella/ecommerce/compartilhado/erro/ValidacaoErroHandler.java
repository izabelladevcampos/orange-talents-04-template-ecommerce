package br.com.zupacademy.izabella.ecommerce.compartilhado.erro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ValidacaoErroHandler {

    @Autowired
    MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class,})
    public List<ValidacaoErro> handle(MethodArgumentNotValidException exception) {

        List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();

        List<ValidacaoErro> Erros = new ArrayList<>();

        fieldError.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ValidacaoErro validacaoErro = new ValidacaoErro(e.getField(), mensagem);

            Erros.add(validacaoErro);
        });

        return Erros;

	}

}
