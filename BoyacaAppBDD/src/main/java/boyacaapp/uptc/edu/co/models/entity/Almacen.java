package boyacaapp.uptc.edu.co.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity 
@Table(name="almacenes")
@Data
@EqualsAndHashCode(exclude = "listaProductos") 
public class Almacen implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_almacen;
	
	@Column(name ="al_codigo_postal",nullable = false)
	private String codigo_postal;
	
	@Column(name ="al_nombre_Persona_Acargo",nullable = false)
	private  String nombrePersonaAcargo;
	
	
	@Column(name ="al_email",nullable = false)
	private String email;
	
	@Column(name ="al_telefono",nullable = false, length = 10)
	private String telefono;
	
	
	@OneToOne
	@JsonIgnoreProperties("direccion")
	private Direccion direccion;
	
	@ManyToOne
	@JoinColumn(name ="id_empresa")
	@JsonIgnoreProperties("listaDeAlamacenes")
	private Empresa empresa;
	
	
	@OneToMany(mappedBy = "almacen",fetch = FetchType.LAZY)
	@JsonIgnoreProperties("almacen")
	private List<Producto> listaProductos = new ArrayList<>();
	
}
