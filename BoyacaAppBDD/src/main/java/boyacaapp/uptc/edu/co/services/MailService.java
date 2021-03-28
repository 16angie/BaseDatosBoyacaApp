package boyacaapp.uptc.edu.co.services;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {
	
	private String emisor;
	private String receptor;
	private Properties props;
	
	private Session session;
	private Message msg;
	
	public void crearPropiedadesMail() {
		// definir el puerto, protocolo, autorizacion
		   this.props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   //definir emisor y receptor
		
	}
	
	public void definirEmisor(String correoEmisor, String contrasena) {
		this.emisor = correoEmisor;
		// correo: "ramirezkarlos9@gmail.com"
		//contra: "dulcemaria111"
		   	  session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication(correoEmisor,contrasena);
		      }
		   });
	}
	
	public void definirReceptor(String correoReceptor) {
		this.receptor = correoReceptor;
	}
	
	public void construirMensaje(String asunto, String contenido) throws AddressException, MessagingException {
		   msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress(emisor, false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receptor));
		   msg.setSubject(asunto);
		   msg.setContent(contenido, "text/html");
		   msg.setSentDate(new Date());
	}
	
	public void enviarMensaje() throws MessagingException {
		Transport.send(msg);
	}

}
