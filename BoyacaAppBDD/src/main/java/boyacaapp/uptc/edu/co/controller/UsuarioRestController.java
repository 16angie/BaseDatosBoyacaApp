package boyacaapp.uptc.edu.co.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import boyacaapp.uptc.edu.co.dto.UsuarioBasicoDto;
import boyacaapp.uptc.edu.co.models.entity.Cliente;
import boyacaapp.uptc.edu.co.models.entity.Usuario;
import boyacaapp.uptc.edu.co.services.IUsuariosService;

@CrossOrigin(origins= {"http://localhost:4200"}) // este es para comentariar al frontend
@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {
	//server.servlet.contextPath=/app
	
	@Autowired
	IUsuariosService usuarioservice;
	
	
	@GetMapping("/loginusuarios")
	public UsuarioBasicoDto show(@RequestParam(value = "email") String correo,@RequestParam(value = "contrasena") String contrasena){
        Usuario usuario = usuarioservice.findByCorreo(correo);
        UsuarioBasicoDto usuariodto = new UsuarioBasicoDto();
        if(usuario != null && usuario.getContrasena().equals(contrasena) && usuario.getEmail().equals(correo) ) {
        usuariodto.setId(usuario.getId());
        usuariodto.setOk(true);
        usuariodto.setTipousuario(usuario instanceof Cliente?"Cliente":"Representante");
        return usuariodto;
        }else {
            //UsuarioBasicoDto usuariodto = new UsuarioBasicoDto();
            usuariodto.setId((long) 0);
            usuariodto.setOk(false);
            return usuariodto;
        }
    }
	
}
