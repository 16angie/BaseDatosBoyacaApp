package boyacaapp.uptc.edu.co.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import boyacaapp.uptc.edu.co.services.IUsuariosService;
import boyacaapp.uptc.edu.co.services.MailService;

@RestController
@RequestMapping("/mails")
public class MailRestController {

	@Autowired
	MailService mailService;
	
	@Autowired
	IUsuariosService usuariosService;
	
	
	
	// true si se envio, false si no
	@GetMapping(value = "/enviarcodigo")
	public boolean enviarCodigo(@RequestParam(value = "email") String correoRecepetor) {
		boolean success= true;
		if(usuariosService.findByCorreo(correoRecepetor) != null) {
			mailService.crearPropiedadesMail();
			mailService.definirEmisor();
			mailService.definirReceptor(correoRecepetor);
			try {
				mailService.construirMensaje();
			} catch (MessagingException e) {
				//e.printStackTrace();
				System.out.println("no se pudo crear el mensaje error de datos");
			}
			
			try {
				mailService.enviarMensaje();
			} catch (MessagingException e) {
				//e.printStackTrace();
				System.out.println("no se pudo enviar el mensaje");
			}
			success= true;
		}else {
			success = false;
		}
		return success;
	}
	
	@GetMapping(value = "/verificarcodigo")
	public boolean verificarCodigo(@RequestParam(value = "codigo")long codigoInput) {
		Long codigos = mailService.getNumero();
		if (codigos == codigoInput) {
			return true;
		}else {
			return false;
		}
	}
}