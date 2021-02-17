package boyacaapp.uptc.edu.co.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity 
@Table(name="especificacion")
@Data
public class EspecificacionProducto {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProducto;
	
	@Column(name ="esp_nombre",nullable = false)
	private String nombre;
	
	@Column(name ="eso_cantidad",nullable = false)
	private int cantidad;
}
