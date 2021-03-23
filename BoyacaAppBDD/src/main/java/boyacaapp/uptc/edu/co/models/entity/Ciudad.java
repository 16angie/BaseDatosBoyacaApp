package boyacaapp.uptc.edu.co.models.entity;

import java.io.Serializable;

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
import lombok.EqualsAndHashCode;

@Entity 
@Table(name="ciudades")
@Data
@EqualsAndHashCode(exclude = "departamento")
public class Ciudad  implements Serializable{
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_ciudad;
	
	@Column(name ="ci_nombre",nullable = false, unique = true)
	private String nombre;
	
	
	@ManyToOne
	@JoinColumn(name ="id_departamento")
	@JsonIgnoreProperties("listaCiudades")
	private Departamento departamento;
	
	
}