package br.com.zupacademy.izabella.ecommerce.compartilhado.email;

//import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;*/
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class Mailer {

	@Autowired
	private JavaMailSender mailSender;
	
	/*@EventListener
	public void teste(ApplicationReadyEvent event) {
		this.enviarEmail("testesizap@gmail.com",
				Arrays.asList("victor_hsc@hotmail.com", "izabellamdoliveira@gmail.com"), "testando", "Olá <br/>Funfou!!!.");
		System.out.println("Terminado o envio de e-mail...");
	}
*/
	public void enviarEmail(String remetente, String destinatario, String assunto, String mensagem) {

		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(remetente);
			helper.setTo(destinatario);
			helper.setSubject(assunto);
			helper.setText(mensagem, true);

			mailSender.send(mimeMessage);

		} catch (MessagingException e) {
			throw new RuntimeException("Problemas com o envio de e-mail!", e);
		}

	}

}
