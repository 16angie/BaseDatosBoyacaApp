package boyacaapp.uptc.edu.co.models.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="usuario")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name ="us_tipo")
@Data
/**
 * Se define un usuario general del sistema, con sus características básicas.
 * "Codigo de seguridad" establece el código que se envía para recuperar cuenta de usuario.
 * "estado" define si se encuentra Activo o Inactivo dentro del sistema.
 * "email" y "contraseña" definen las credenciales necesarias para ingreso al sistema.
 * @author Diian_Ramirez
 *
 */
public  class Usuario  {
	
	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="us_email",nullable = false, unique = true)
	private String email;
	
	@Column(name ="us_contrasenia",nullable = false)
	private String contrasena;
	
	@Column(name ="us_nombre",nullable = false)
	private String nombre;
	
	@Column(name ="us_apellido",nullable = false)
	private String apellido;
	
	@Column(name ="us_numero_telefono",nullable = true)
	private String numeroTelefonico;
	
	@Column(name ="us_numero_cedula",nullable = true, unique = true)
	private String numerodecedula;
	
	@OneToOne
	private Imagen imagen;
	
	@Column(name ="codigoSerguridad",nullable = true, unique = true)
	private Long codigoSeguridad;
	
	@Column(name ="us_estado",nullable = true)
	@Enumerated(value = EnumType.STRING)
	private EstadoObjetoBD estadoObjeto = EstadoObjetoBD.ACTIVO;
}