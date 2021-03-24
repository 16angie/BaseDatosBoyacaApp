package boyacaapp.uptc.edu.co.models.entity;



import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="representante_comercial")
@Data
@DiscriminatorValue(value="REPRESENTANTECOMERCIAL")
public class RepresentanteComercial extends Usuario {

	/**
	 * 
	 */
	
	@Column(name ="rep_nombre_negocio",nullable = false)
	private String nombreNegocio;
	
	
	@OneToOne
	private Empresa empresa;
	
	@OneToOne
	private CuentaBancaria cuenta;
	

}
