package boyacaapp.uptc.edu.co.models.entity;

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
	
	private int cantidad;
	
	private double precio;
	
	private int costoEnvio;
	//calculado de peso
	
	@ManyToOne
	@JoinColumn(name = "id_factura_detalles")
	private FacturaCompra factura;
	
	@ManyToOne
	@JoinColumn(name = "id_producto_detalles")
	private Producto producto;
}
