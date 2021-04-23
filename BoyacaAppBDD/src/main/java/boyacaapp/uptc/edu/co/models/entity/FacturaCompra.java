package boyacaapp.uptc.edu.co.models.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity 
@Table(name = "compras")
@Data
public class FacturaCompra implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_compra;
	
	@Column(name="com_fecha_compra",nullable = false)
	@Temporal(TemporalType.DATE)
	private Calendar fecha_compra;
	
	@Column(name ="com_valor_total",nullable = true)
	private double valor_total_compra;
	// este valor se calcula
	
	@Column(name="com_referencia_pago")
	private String referenciaDeCompra; // va a ser generado automaticamente ????
	
	@Column(name="com_estado_de_compra", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private EstadoCompra estadodelacompra = EstadoCompra.EN_PROCESO;
	

	
	@ManyToOne
	private Cliente cliente;
	
	
	@OneToMany
	private List<DetalleCompra> detalleCompra;
	
	
	@OneToOne
	private Domicilio domicilioCompra;
	
	@OneToOne
	private Envio envio;
	
	
	public void generarReferencia() {
		referenciaDeCompra +=this.id_compra+cliente.getEmail().substring(0,3)
				+fecha_compra.getWeekYear()+detalleCompra.get(0).getProducto().getNombre().substring(0, 5);
	}
	
}
