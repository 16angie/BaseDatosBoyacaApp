package boyacaapp.uptc.edu.co.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Table(name="cuentas")
@Data
public class CuentaBancaria {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_Cuenta;
	
	@Column(name="cu_nombre_titular", nullable = false)
	private String nombreTitular;
	
	@Column(name="cu_numero_cuenta", nullable = false)
	private String numeroCuenta;
	

	@Column(name="cu_nit_titular", nullable = false)
	private String nitTitular;
	
	/**
	@Column(name="cu_certificado_cuenta", nullable = false)
	private Document certificadoCuenta;
	**/
	
	@Column(name="cu_tipoBanco", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private TipoBanco tipoBanco;
	
	
	@Column(name="cu_nit_tipoCuenta", nullable = false)
	private String tipoCuenta;
	
	@Column(name="cu_accountIdPayu", nullable = false)
	private int accountIdPayu;
	
	@Column(name="cu_merchantIdPayu", nullable = false)
	private int mechantIdPayu;
	
}
