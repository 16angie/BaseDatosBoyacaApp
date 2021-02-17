package boyacaapp.uptc.edu.co.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="departamentos")
@Data
public class Departamento implements Serializable{
	
	
	/**
	 * 
	 */
	
	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_departamento;
	
	@Column(name ="dep_nombre",nullable = false, unique = true)
	private String nombre;
	
	@OneToMany
	private List<Ciudad> listaCiudades;
	
	
	public void agregarListaCiudades(Ciudad ciudad) {
		this.listaCiudades.add(ciudad);
	}
	
	private static final long serialVersionUID = 1L;

}
