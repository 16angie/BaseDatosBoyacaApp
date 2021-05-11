package boyacaapp.uptc.edu.co.models.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Entity 
@Table(name = "compras")
@Data

/**
 * FacturaCompra defina una compra hecha por un Cliente.
 * la compra necesita sus detalles y quen hizo la compra, además del envío y el domicilio donde llegará. 
 * @author Diian_Ramirez
 */
public class FacturaCompra implements Serializable{
		
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_compra;
	
	@Column(name="com_fecha_compra",nullable = true)
	@Temporal(TemporalType.DATE)
	private Calendar fecha_compra = GregorianCalendar.getInstance();
	
	@Column(name ="com_valor_total",nullable = false)
	private double valor_total_compra;
	// este valor se calcula
	
	@Column(name="com_referencia_pago",nullable = true)
	private String referenciaDeCompra ; 
	
	@Column(name="com_estado_de_compra", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private EstadoCompra estadodelacompra = EstadoCompra.EN_PROCESO;
	
	@ManyToOne
	private Cliente cliente;
		
	//------
	@OneToMany(mappedBy = "facturaCompra",fetch = FetchType.LAZY)
	@JsonIgnoreProperties("facturaCompra")
	private List<Envio>envios;

}