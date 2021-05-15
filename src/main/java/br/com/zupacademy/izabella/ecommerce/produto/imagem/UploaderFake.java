package br.com.zupacademy.izabella.ecommerce.produto.imagem;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Primary
public class UploaderFake implements Uploader{
	
	/**
     * recolhe as imagens passadas como parametro e devolve um Set com os links
     * @param imagens lista de imagens do tipo MultiPartFile
     * @return links para imagens que foram carregadas
     */
    public Set<String> envia(List<MultipartFile> imagens) {
        return imagens.stream()
                .map(img -> "http://bucket.io/"+ img.getOriginalFilename())
                .collect(Collectors.toSet());

    }
}
