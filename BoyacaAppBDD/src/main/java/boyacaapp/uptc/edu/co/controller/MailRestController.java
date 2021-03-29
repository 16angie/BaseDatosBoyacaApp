package boyacaapp.uptc.edu.co.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import boyacaapp.uptc.edu.co.models.entity.Cliente;
import boyacaapp.uptc.edu.co.models.entity.RepresentanteComercial;
import boyacaapp.uptc.edu.co.models.entity.Usuario;
import boyacaapp.uptc.edu.co.services.IClienteService;
import boyacaapp.uptc.edu.co.services.IRepresentanteComercialService;
import boyacaapp.uptc.edu.co.services.IUsuariosService;
import boyacaapp.uptc.edu.co.services.MailService;

@RestController
@RequestMapping("/mails")
public class MailRestController {

	@Autowired
	MailService mailService;
	
	@Autowired
	IUsuariosService usuariosService;
	
	@Autowired
	IClienteService clienteservice;
	
	@Autowired
	IRepresentanteComercialService representanteserive;
	
	
	// true si se envio, false si no
	@GetMapping(value = "/enviarcodigo")
	public boolean enviarCodigo(@RequestParam(value = "email") String correoRecepetor) {
		boolean success= true;
		Usuario usuario = usuariosService.findByCorreo(correoRecepetor);
	
		if(usuario != null) {
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
				if(usuario instanceof Cliente) {
					usuario.setCodigoSeguridad(mailService.getNumero());
					clienteservice.save((Cliente) usuario);
				}else {
					usuario.setCodigoSeguridad(mailService.getNumero());
					representanteserive.save((RepresentanteComercial) usuario);
				}
				
			} catch (MessagingException e) {
				System.out.println("no se pudo enviar el mensaje");
			}
			success= true;
		}else {
			success = false;
		}
		return success;
	}
	
	@GetMapping(value = "/verificarcodigo")
	public boolean verificarCodigo(@RequestParam(value = "codigo")long codigoInput,@RequestParam(value = "email") String correoRecepetor,@RequestParam(value = "contrasena") String contra) {
		Usuario usuario = usuariosService.findByCorreo(correoRecepetor);
		System.out.println(usuario.getCodigoSeguridad());
		if (usuario.getCodigoSeguridad() == codigoInput) {
			if(usuario instanceof Cliente) {
				usuario.setContrasena(contra);
				clienteservice.save((Cliente) usuario);
			}else {
				usuario.setContrasena(contra);
				representanteserive.save((RepresentanteComercial) usuario);
			}
			return true;
		}else {
			return false;
		}
	}
}