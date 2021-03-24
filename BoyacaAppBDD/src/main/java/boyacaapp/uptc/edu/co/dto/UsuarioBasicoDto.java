package boyacaapp.uptc.edu.co.dto;

import java.io.Serializable;


import lombok.Data;

@Data
public class UsuarioBasicoDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String tipousuario;
	
	private boolean ok;
	

}
