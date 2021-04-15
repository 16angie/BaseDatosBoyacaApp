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
public class EspecificacionProducto {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEspecificacion;
	
	@Column(name ="esp_nombre",nullable = false)
	private String nombreEspecificacionPrimaria;
	
	@Column(name ="esp_detalle",nullable = false)
	private String detalleEspecificacionPrimaria;
	
	
	@Column(name ="esp_nombre_secundario",nullable = true)
	private String nombreEspecificacionsecundaria;
	
	@Column(name ="esp_detalle_secundario",nullable = true)
	private String detallesecundario;
	
	
	@Column(name ="eso_cantidad_primaria",nullable = false)
	private int cantidadEspecificacionPrimaria;
	
	
	// ejemplo   nombreEspecificacionPrimaria talla //  detalleEspecificacionPrimaria(L) 
	// cantidadEspecificacionPrimaria = 3
	// detallesecundario color 
	// amarillo 
	
	
	@ManyToOne
	@JoinColumn(name ="id_prodcuto")
	@JsonIgnoreProperties("listaDeEspecificaciones")
	Producto producto_e;
	
	
	
}