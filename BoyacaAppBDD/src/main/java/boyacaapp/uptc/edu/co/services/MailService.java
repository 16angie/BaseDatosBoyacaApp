package boyacaapp.uptc.edu.co.services;

import org.springframework.stereotype.Service;

import lombok.Data;

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
@Data
public class MailService {
	
	private String emisor;
	private String receptor;
	private Properties props;
	
	private Session session;
	private Message msg;
	private long numero;
	
	public void crearPropiedadesMail() {
		// definir el puerto, protocolo, autorizacion
		   this.props = new Properties();
		   props.put("mail.transport.protocol", "smtp");
		   props.put("mail.smtp.port", "587");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.auth", "true");
		   //props.put("mail.smtp.host", "smtp.gmail.com");
		   //props.put("mail.smtp.host", "smtp.hotmail.com");
		   //definir emisor y receptor
	}
	
	public void definirEmisor() {
			emisor="BoyacaApp@gmail.com";
		   	  this.session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("BoyacaApp@gmail.com","Boyaca_1App.");
		      }
		   });
	}
	
	public void definirReceptor(String correoReceptor) {
		this.receptor = correoReceptor;
	}
	
	public void construirMensaje() throws AddressException, MessagingException {
		numero = (long)(Math.random()*1000000+10000000);
		String asunto = "Cambio de contraseña para tu cuenta en BoyacaApp";
		String contenido ="Este es tu codigo de verificacion:  "+ numero;
		   msg = new MimeMessage(this.session);
		   msg.setFrom(new InternetAddress(emisor, true));
		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receptor));
		   msg.setSubject(asunto);
		   msg.setContent(contenido, "text/html");
		   msg.setSentDate(new Date());
	}
	
	public void enviarMensaje() throws MessagingException {
		Transport.send(msg);
	}
}
