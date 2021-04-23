package boyacaapp.uptc.edu.co.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;


@Entity 
@Table(name="detalles_compra")
@Data
public class DetalleCompra {
	
	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_detalles;
	
	@Column(name ="det_cantidad",nullable = false)
	private int cantidad;
	
	@Column(name ="det_precio",nullable = false)
	private double precio;
	
	@Column(name ="det_costoEnvio",nullable = false)
	private int costoEnvio;
	//calculado de peso y la tablas de empresas de envio
	
	@ManyToOne
	@JoinColumn(name = "id_factura_detalles")
	private FacturaCompra factura;
	
	@ManyToOne
	@JoinColumn(name = "id_producto_detalles")
	private Producto producto;
	
	public void calcularCostoEnvio() {
		
	}
}
