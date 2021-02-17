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
@Table(name="ciudades")
@Data
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
	private Departamento departamento;
	
	
}
