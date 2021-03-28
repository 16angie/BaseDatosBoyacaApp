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
	
	private String codigo = "";
	
	// true si se envio, false si no
	@GetMapping(value = "/enviarcodigo")
	public boolean enviarCodigo(@RequestParam(value = "email") String correoRecepetor) {
		boolean success= true;
		if(usuariosService.findByCorreo(correoRecepetor) != null) {
			codigo =  "hfjhdfb6675hh1u";
			mailService.crearPropiedadesMail();
			mailService.definirEmisor("correo@gmail.com", "password");
			mailService.definirReceptor(correoRecepetor);
			try {
				mailService.construirMensaje("Cambio de contraseña en BoyacApp", "ingrese el siguiente código en el formulario que solicita:" +  codigo);
			} catch (MessagingException e) {
				//e.printStackTrace();
				System.out.println("no e pudo crear el mensaje error de datos");
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
	public boolean verificarCodigo(String codigoInput) {
		if (codigo.equals(codigoInput)) {
			return true;
		}else {
			return false;
		}
	}
}