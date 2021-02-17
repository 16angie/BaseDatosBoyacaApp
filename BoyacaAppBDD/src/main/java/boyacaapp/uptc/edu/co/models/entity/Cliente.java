package boyacaapp.uptc.edu.co.models.entity;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity 
@Table(name = "clientes")
@Data
public class Cliente {

	/**
	 *
	 */
	
	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_cliente;
	
	@Column(name ="cli_email",nullable = false, unique = true )
	private String email;
	
	@Column(name ="cli_nombre",nullable = false)
	private String nombre;
	
	@Column(name ="cli_apellido",nullable = false)
	private String apellido;
	
	@Column(name ="cli_numero_telefono",nullable = true)
	private String numero_telefono;
	
	@Column(name ="cli_contrasenia",nullable = false, unique = true )
	private String contrasena;
	
	@OneToOne
	private Direccion direccion_residencia;
	
	@Column(name ="cli_cedula",nullable = true)
	private String cedula;
	
	@OneToMany
	private List<FacturaCompra> listaFaturas;

}
