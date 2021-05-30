package br.com.zupacademy.izabella.ecommerce.compartilhado.email;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("ecommerce")
public class ApiProperty {

	private final Mail mail = new Mail();

	public Mail getMail() {
		return mail;
	}

	public static class Mail {

		private String host;
		private Integer port;
		private String username;
		private String password;

		public String getHost() {
			return host;
		}

		public Integer getPort() {
			return port;
		}

		public String getUsername() {
			return username;
		}

		public String getPassword() {
			return password;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public void setPort(Integer port) {
			this.port = port;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public void setPassword(String password) {
			this.password = password;
		}

	}

}
