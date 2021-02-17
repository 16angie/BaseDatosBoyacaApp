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
public class Direccion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_direccion;
	
	@Column(name ="dir_barrio",nullable = false)
	private String barrio;
	
	@Column(name ="dir_via",nullable = false)
	private String via;
	
	@Column(name ="dir_numero",nullable = false)
	private int numero; // este numero va dividido en dos en frontend y se une en back
	
	@Column(name ="dir_datosAdicionales",nullable = false)
	private String datosAdicionales;
	
	@ManyToOne
	private Ciudad ciudad;

}
