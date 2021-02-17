package boyacaapp.uptc.edu.co.models.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity 
@Table(name="envios")
@Data
public class Envio {

	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_envio;
	
	@Column(name="env_numero_guia",nullable = false)
	private String numeroGuia;
	
	@Column(name="env_fecha_envio",nullable = false)
	@Temporal(TemporalType.DATE)
	private Calendar fecha_envio;
	
	@Column(name="env_fecha_relativa_llegada",nullable = false)
	@Temporal(TemporalType.DATE)
	private Calendar fecha_relativa_llegada;
	
	@OneToOne
	private RepresentanteComercial representante_hizo_envio;
	
	@Column(name="env_estado_envio", nullable = false)
	@Enumerated(EnumType.STRING)
	private ESTADO_ENVIO estado_envio;
	
	
}
