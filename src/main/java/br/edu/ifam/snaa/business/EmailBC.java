package br.edu.ifam.snaa.business;

import java.io.Serializable;

import javax.inject.Inject;

import br.edu.ifam.snaa.domain.seguranca.Usuario;
import br.edu.ifam.snaa.util.Util;
import br.gov.frameworkdemoiselle.mail.Mail;
import br.gov.frameworkdemoiselle.stereotype.Controller;

@Controller
public class EmailBC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private Mail mail;

	public void enviarRespostaAprovacao(String email, String message) {

		StringBuilder builder = new StringBuilder();

		builder.append("Sua solicitação foi aprovada no sistema<br/>");

		if (Util.isNotNull(message)) {
			builder.append("<b>Mensagem do Administrador:</b> <br/>").append(message);
		}

		mail.to(email).from(email).body().html(builder.toString())
				.subject("SNAA - Solicitação de Criação Conta").importance()
				.high().send();

	}

	public void enviarRespostaReprovacao(String email, String message) {

		StringBuilder builder = new StringBuilder();

		builder.append("Sua solicitação foi reprovada no sistema<br/>");

		if (!Util.isNotNull(message)) {
			builder.append("<b>Mensagem do Administrador:</b> <br/>").append(message);
		}
		mail.to(email).from(email).body().html(builder.toString())
				.subject("SNAA - Solicitação de conta foi aprovada")
				.importance().high().send();

	}

	public void enviarSenha(Usuario usuario) {

		StringBuilder builder = new StringBuilder();

		builder.append("Usuário: ").append(usuario.getNome()).append("<br/>");
		builder.append("Senha: ").append(usuario.getSenha()).append("<br/>");

		mail.to(usuario.getEmail()).from(usuario.getEmail()).body()
				.html(builder.toString())
				.subject("SNAA - Solicitação de recuperação de senha")
				.importance().high().send();

	}
}
