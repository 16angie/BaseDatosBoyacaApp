package boyacaapp.uptc.edu.co.models.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity 
@Table(name="envios")
@Data

/**
 * el Envío define un nvío de uno o varios productos a un destinatario
 * el estado del envío especifica en que estado se encuentra en un momento determinado
 * @author Diian_Ramirez
 *
 */
public class Envio {

	
	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_envio;
	
	@Column(name="env_numero_guia",nullable = true)
	private String numeroGuia;
	
	@Column(name="env_fecha_envio",nullable = true)
	@Temporal(TemporalType.DATE)
	private Calendar fecha_envio;
	
	@Column(name="env_fecha_relativa_llegada",nullable = true)
	@Temporal(TemporalType.DATE)
	private Calendar fecha_relativa_llegada;
	
	@Column(name="env_estado_envio", nullable = true)
	@Enumerated(EnumType.STRING)
	private EstadoEnvio estado_envio = EstadoEnvio.EN_PROCESO;
	
	// sacarlo del id  de la emrpresa 
	@OneToOne
	private RepresentanteComercial representante_hizo_envio;
		
	
	@OneToMany(mappedBy = "envio_c",fetch = FetchType.LAZY)
	private List<DetalleCompra> detalleCompra;
	
	@OneToOne
	private Domicilio domicilio;
	
	@ManyToOne
	@JoinColumn(name ="id_envio_factura")
	@JsonIgnoreProperties("envios")
	private FacturaCompra facturaCompra;
}