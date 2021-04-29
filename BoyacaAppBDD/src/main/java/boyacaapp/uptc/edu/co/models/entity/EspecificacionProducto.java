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
@Table(name="especificacion")
@Data

/**
 * la especificación define características numéricas y contables de un producto.
 * ejemplo:  nombreEspecificacionPrimaria: talla //  detalleEspecificacionPrimaria : L //cantidadEspecificacionPrimaria = 3;
	 nombreEspecifSecundaria: color // detallesecundario:  amarillo // cantidad: 3.
	 El resultado del ejemplo sería: del producto X, De la talla L y el color amarillo hay 3.
 * @author Diian_Ramirez
 *
 */
public class EspecificacionProducto {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEspecificacion;
	
	@Column(name ="esp_nombre",nullable = true)
	private String nombreEspecificacionPrimaria;
	
	@Column(name ="esp_detalle",nullable = true)
	private String detalleEspecificacionPrimaria;
	
	@Column(name ="esp_nombre_secundario",nullable = true)
	private String nombreEspecificacionsecundaria;
	
	@Column(name ="esp_detalle_secundario",nullable = true)
	private String detallesecundario;
	
	
	@Column(name ="eso_cantidad",nullable = false)
	private int cantidad;
	
	@ManyToOne
	@JoinColumn(name ="id_prodcuto")
	@JsonIgnoreProperties("listaDeEspecificaciones")
	Producto producto_e;
}