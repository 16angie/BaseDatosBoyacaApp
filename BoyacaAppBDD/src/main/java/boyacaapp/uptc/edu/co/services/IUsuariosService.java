package boyacaapp.uptc.edu.co.services;

import boyacaapp.uptc.edu.co.models.entity.Usuario;

public interface IUsuariosService {
	
	public Usuario findByCorreo(String correo);
	

}
