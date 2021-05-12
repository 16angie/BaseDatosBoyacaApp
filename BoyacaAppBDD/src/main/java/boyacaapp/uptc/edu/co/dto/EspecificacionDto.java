package boyacaapp.uptc.edu.co.dto;

import lombok.Data;

@Data
public class EspecificacionDto {
	

	private Long idEspecificacion;
	
	private String nombreEspecificacionPrimaria;
	
	private String detalleEspecificacionPrimaria;
	
	private String nombreEspecificacionsecundaria;
	
	private String detallesecundario;
	
	private int cantidad;
	
	private Long idProducto;
}
