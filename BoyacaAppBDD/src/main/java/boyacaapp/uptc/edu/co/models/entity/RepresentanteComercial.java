package boyacaapp.uptc.edu.co.models.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name="representante_comercial")
@Data
@DiscriminatorValue(value="REPRESENTANTECOMERCIAL")
/**
 * El Representante Comercial es aquel que está al frente de una empresa y quién hará manejo de 
 * la empresa, los almacenes y los productos que sus empresa ofrece.
 * @author Diian_Ramirez
 */
public class RepresentanteComercial extends Usuario {

	@Column(name ="rep_nombre_negocio",nullable = false)
	private String nombreNegocio;
	
	@OneToOne(mappedBy = "representante",fetch = FetchType.LAZY)
	@JsonIgnoreProperties("representante")
	private Empresa empresa;
	
	@OneToOne
	private CuentaBancaria cuenta;
}