package boyacaapp.uptc.edu.co.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import boyacaapp.uptc.edu.co.models.dao.IUsarioDao;
import boyacaapp.uptc.edu.co.models.entity.Usuario;


@Service
public class UsuarioServiceImpl implements IUsuariosService{
		
	@Autowired
	private IUsarioDao usuariodao;

	public Usuario findByCorreo(String correo) {
		return usuariodao.findByEmail(correo);
	}
	
}
