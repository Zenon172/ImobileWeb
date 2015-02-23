package br.com.sysfar.imobileweb.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import br.com.topsys.exception.TSApplicationException;

public class EmailUtil {

	private String email;
	private String senha;
	private String smtp;
	private String porta;

	public EmailUtil() {

		this.porta = "587";
		this.email = "contato@sistemaimobileweb.com.br";
		this.senha = "na5@MAyaT3ra";
		//this.smtp = "mail.sistemaimobileweb.com";
		this.smtp = "localhost";

	}

	public void enviar(String destinatario, String assunto, String mensagem) throws TSApplicationException {

		try {

			Properties props = new Properties();

			props.put("mail.debug", "false");
			props.put("mail.smtp.host", smtp);
			props.put("mail.smtp.port", porta);
			props.put("mail.smtp.auth", "true");
			//props.put("mail.smtp.ssl.enable", "false");
			//props.put("mail.smtp.starttls.required", "true");

			Authenticator auth = new Authenticator() {

				@Override
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(email, senha);
				}

			};

			Session session = Session.getInstance(props, auth);

			MimeMessage msg = new MimeMessage(session);
			Multipart multiPart = new MimeMultipart();

			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(mensagem, "text/html; charset=utf-8");

			multiPart.addBodyPart(htmlPart);

			msg.setContent(multiPart);
			msg.setSubject(assunto);
			msg.setFrom(new InternetAddress(this.email));
			msg.addRecipients(Message.RecipientType.TO, destinatario);

			Transport.send(msg);

		} catch (Exception e) {

			e.printStackTrace();
			throw new TSApplicationException("ERRO.ENVIO.EMAIL", e);

		}

	}

}
