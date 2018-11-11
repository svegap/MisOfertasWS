package com.misofertas.data.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.URLDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;


import com.misofertas.data.model.Usuario;

public class EnvioMail {
	@Context
    private UriInfo context;
	
	public EnvioMail() {

	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public  String getMail(Usuario usuario) throws MalformedURLException    {

		final String username = "mofertasdc@gmail.com";
		final String password = "duoc2018";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Cargamos la configuración del correo desde un archivo localizable via CLASSPATH

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("mofertasdc@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usuario.getEmail()));
			message.setSubject("Newsletter Ofertas");

			// Para adjuntar un archivo debe ser con Multipart
			Multipart multipart = new MimeMultipart();

			// Creamos el cuerpo del mensaje en html
			BodyPart messageBodyPart = new MimeBodyPart();

			DataSource fs = new URLDataSource(new URL("https://misofertasdiag.blob.core.windows.net/misofertas/Newsletter.html"));
			messageBodyPart.setDataHandler(new DataHandler(fs));
			messageBodyPart.setHeader("Content-ID", "<html>");
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			//enviamos el Mensaje
			Transport.send(message);


			return "<result>Exito</result>";

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
 
}

