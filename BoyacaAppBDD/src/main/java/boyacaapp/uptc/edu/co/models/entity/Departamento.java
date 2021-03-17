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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="departamentos")
@Data 
@EqualsAndHashCode(exclude = "listaCiudades") 
public class Departamento implements Serializable{
	
	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_departamento;
	
	@Column(name ="dep_nombre",nullable = false, unique = true)
	private String nombre;
	
	
	@OneToMany(mappedBy = "departamento",fetch = FetchType.LAZY)
	@JsonIgnoreProperties("departamento")
	private List<Ciudad> listaCiudades = new ArrayList<>();
	
	
	public void agregarCiudad(Ciudad ciudad){
		listaCiudades.add(ciudad);
	}
	
	private static final long serialVersionUID = 1L;

}
