package boyacaapp.uptc.edu.co.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity 
@Table(name="Imagenes")
@Data
public class Imagen {

	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_imagen;
	
	@Column(name = "nombreimagen",nullable = false)
	private String nombre;
	
		
}
