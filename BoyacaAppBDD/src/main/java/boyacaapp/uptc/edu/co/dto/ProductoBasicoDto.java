package boyacaapp.uptc.edu.co.dto;

import boyacaapp.uptc.edu.co.models.entity.Imagen;
import lombok.Data;

@Data
public class ProductoBasicoDto {
	
	private Long idProducto;
	private String nombre;
	private double precio;
	private Imagen imagenIlustrativa;
	private String genero; 
	private int stock;

}