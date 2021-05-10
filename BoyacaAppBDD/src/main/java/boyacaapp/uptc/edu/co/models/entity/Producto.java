package boyacaapp.uptc.edu.co.models.entity;
import java.util.ArrayList;
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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity 
@Table(name="productos")
@Data
/**
 * Se define un producto que será ofertado y/o adquirido.
 * "genero" define la categoría de clasificación del producto.
 * @author Diian_Ramirez
 *
 */
public class Producto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProducto;
	
	@Column(name ="pro_estado",nullable = true)
	@Enumerated(value = EnumType.STRING)
	private EstadoObjetoBD estadoObjeto = EstadoObjetoBD.ACTIVO;
	
	@Column(name ="pro_nombre",nullable = false)
	private String nombre;
	
	@Column(name ="pro_precio",nullable = false)
	private double precio;
	
	@Column(name ="pro_genero",nullable = false)
	@Enumerated(value = EnumType.STRING)
	private CategoriaProductos genero;
	
	@Column(name ="pro_stock_total",nullable = true)
	private int stock_total;
	
	@Column(name ="pro_peso",nullable = false)
	private double peso;
	
	@Column(name ="pro_precio_envio",nullable = true)
	private double precio_envio;

	@ManyToOne
	@JoinColumn(name ="id_almacen")
	@JsonIgnoreProperties("listaProductos")
	private Almacen almacen;
		
	@OneToMany(mappedBy = "producto",fetch = FetchType.LAZY)
	@JsonIgnoreProperties("producto")
	private List<Caracteristica> listaCaracteristicas = new ArrayList<Caracteristica>();
	
	@OneToMany(mappedBy = "producto_e",fetch = FetchType.LAZY)
	@JsonIgnoreProperties("producto_e")
	private List<EspecificacionProducto> listaDeEspecificaciones = new ArrayList<EspecificacionProducto>();
	
	@OneToMany
	private List<Imagen> listaImagenes = new ArrayList<Imagen>();
	
	
	
	/*
	 * este metodo se hace para calcular el estock total del producto 
	 */
	public  void calcularStockTotal() {
		if(!listaDeEspecificaciones.isEmpty()) {
			for (EspecificacionProducto esp : listaDeEspecificaciones) {
				this.stock_total+= esp.getCantidad();
			}
		}
	}
}
