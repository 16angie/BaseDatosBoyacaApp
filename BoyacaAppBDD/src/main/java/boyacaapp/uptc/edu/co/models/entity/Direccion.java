package boyacaapp.uptc.edu.co.models.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;


@Entity 
@Table(name="direcciones")
@Data

/**
 * la clase dirección registra una ubicación dentro de una ciudad específica
 * por tanto depende de una ciudad para poderse establecer
 */
public class Direccion implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_direccion;
	
	@Column(name ="dir_barrio",nullable = false)
	private String barrio;
	
	@Column(name ="dir_via",nullable = false)
	private String via;
	
	@Column(name ="dir_numero",nullable = false)
	private String numero; 
	
	@Column(name ="dir_datosAdicionales",nullable = false)
	private String datosAdicionales;
	
	@ManyToOne
	private Ciudad ciudad;
}