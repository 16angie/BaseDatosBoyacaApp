package boyacaapp.uptc.edu.co.models.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.Data;


@Entity 
@Table(name = "representates_comerciales")
@Data
public class RepresentanteComercial {

	/**
	 * 
	 */
	
	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_representantes;
	
	@Column(name ="rep_email",nullable = false, unique = true )
	private String email;
	
	@Column(name ="rep_nombre",nullable = false)
	private String nombre;
	
	@Column(name ="rep_apellido",nullable = false)
	private String apellido;
	
	@Column(name ="rep_numero_telefono",nullable = true)
	private String numero_telefono;
	
	@Column(name ="rep_contrasenia",nullable = false, unique = true )
	private String contrasena;
	
	@OneToOne
	private Direccion direccion_residencia;
	
	@Column(name ="rep_nombre_negocio",nullable = false)
	private String nombreNegocio;
	
	@OneToOne
	private Empresa empresa;
	
	/**
	@Column(name ="re_doc_cedula",nullable = true)
	private Document documento_cedula;
	**/
	
	@Column(name ="rep_numero_cedula",nullable = true)
	private Long numerodecedula;
	
	@OneToOne
	private CuentaBancaria cuenta;
	
	

}
