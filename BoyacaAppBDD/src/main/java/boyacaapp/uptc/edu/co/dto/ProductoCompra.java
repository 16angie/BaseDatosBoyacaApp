package boyacaapp.uptc.edu.co.dto;


import boyacaapp.uptc.edu.co.models.entity.Imagen;
import lombok.Data;

@Data
public class ProductoCompra {

	
	private Long idProducto;
	private String nombre;
	private double precio;
	private Imagen imagenIlustrativa;
	private String genero; 
	private String nombreEspecificacionPrimaria;
	private String detalleEspecificacionPrimaria;
	private String nombreEspecificacionsecundaria;
	private String nombreDetalleSecundario;
	private double precioEnvio;
	private int cantidad;
	private double peso;
	private Long id_especificacion;
	
}
