package boyacaapp.uptc.edu.co.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@Column(name ="elec_nombre",nullable = true)
	private String nombreEspecificacionPrimaria;
	
	@Column(name ="elec_detalle",nullable = true)
	private String detalleEspecificacionPrimaria;
	
	@Column(name ="elec_nombre_secundario",nullable = true)
	private String nombreEspecificacionsecundaria;
	
	@Column(name ="elec_detalle_secundario",nullable = true)
	private String nombreDetalleSecundario;
	
	@Column(name ="elec_cantidad_primaria",nullable = false)
	private int cantidad;
	
	@Column(name ="elec_precio",nullable = false)
	private double precio; 
	
	@Column(name ="elec_precio_envio",nullable = false)
	private double precioEnvio;
	
	@Column(name ="elec_peso",nullable = true)
	private double peso;

	@Column(name ="elec_id_especificacion",nullable = true)
	private Long id_especificacion;
	
	@Column(name ="elec_id_producto",nullable = true)
	private Long idProducto;
	
	@ManyToOne
	@JoinColumn(name = "id_factura_detalles")
	@JsonIgnoreProperties("detalleCompra")
	private FacturaCompra factura;
}