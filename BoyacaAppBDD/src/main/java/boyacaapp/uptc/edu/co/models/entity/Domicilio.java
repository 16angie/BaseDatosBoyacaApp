package boyacaapp.uptc.edu.co.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity 
@Table(name="domicilio")
@Data

/**
 * un domicilio especifica a nombre de quien se realizará un envío 
 * por lo tanto necesita una dirección a donde llegará y además los datos del receptor del envío
 */
public class Domicilio implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_domicilio;
	
	@Column(name ="dom_nombre",nullable = false)
	private String nombre;
	
	@Column(name ="dom_apellido",nullable = false)
	private String apellido;
	
	@Column(name ="dom_numero_telefono",nullable = true)
	private String numero_telefono;
	
	@OneToOne
	private Direccion direccion;
	
	@OneToOne
	private Envio envio;
}