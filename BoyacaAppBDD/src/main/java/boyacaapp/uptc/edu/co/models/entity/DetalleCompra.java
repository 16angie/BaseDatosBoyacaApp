package boyacaapp.uptc.edu.co.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity 
@Table(name="detalles_compra")
@Data
public class DetalleCompra {
	
	/**
	 * Define un detalle de una compra, ejemplo: "1 ruana $30000"
	 * la compra puede tener muchos detalles de compra
	 */
	
	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_detalles;

	@Column(name ="elec_cantidad_primaria",nullable = false)
	private int cantidad;
	
	@OneToOne
	private EspecificacionProducto idEspecificacionElegida;
	
	@OneToOne
	private Producto idProducto;
	
	
	@ManyToOne
	@JoinColumn(name ="envio_compra")
	@JsonIgnoreProperties("detalleCompra")
	private Envio envio_c;
	
	
}