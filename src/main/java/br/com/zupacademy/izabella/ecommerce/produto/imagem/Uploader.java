package br.com.zupacademy.izabella.ecommerce.produto.imagem;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public interface Uploader {

	public Set<String> envia(List<MultipartFile> imagens);
}
