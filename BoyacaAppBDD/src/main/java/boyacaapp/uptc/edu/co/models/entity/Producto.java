package boyacaapp.uptc.edu.co.models.entity;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity 
@Table(name="productos")
@Data
public class Producto {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProducto;
	
	
	@Column(name ="pro_nombre",nullable = false)
	private String nombre;
	
	@Column(name ="pro_precio",nullable = false)
	private double precio;
	
	@Column(name ="pro_genero",nullable = false)
	@Enumerated(value = EnumType.STRING)
	private Categoria genero;
	
	@Column(name ="pro_stock_total",nullable = true)
	private int stock_total;
	// esta es la suma de las especificaciones calculable
	
	@Column(name ="pro_peso",nullable = false)
	private double peso;
	
	@Column(name ="pro_precio_envio",nullable = false)
	private double precio_envio;
	
	@OneToMany
	@Column(name ="pro_detalles_producto",nullable = false)
	private List<Caracteristica> detalle ;

	@OneToMany
	private List<Caracteristica> Caracteristica ;
	
	@Column(name ="pro_caracterisicas_des",nullable = false)
	private ArrayList<String> descripcion_algunas_caracteristicas;
	
	@OneToMany
	private List<EspecificacionProducto> listaDeEspecificaciones;
	
	@OneToMany
	private List<Imagen> listaImagenes;
}
