package boyacaapp.uptc.edu.co.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity 
@Table(name="caracteristicas")
@Data
public class Caracteristica {
	
	
	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_caracteristica;
	
	@Column(name ="ca_nombre",nullable = false)
	private String nombre;
	
	@Column(name ="ca_descripcion",nullable = false)
	private String descripcion;
	
	
	@ManyToOne
	@JoinColumn(name ="id_producto")
	@JsonIgnoreProperties("listaCaracteristicas")
	private Producto producto;
	
}
