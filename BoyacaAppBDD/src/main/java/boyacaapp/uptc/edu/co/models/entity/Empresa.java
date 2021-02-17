package boyacaapp.uptc.edu.co.models.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity 
@Table(name="empresas")
@Data
public class Empresa  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_empresa;
	
	@Column(name="em_nombre_representante_legal", nullable = false)
	private String nombreRepresentanteLegal;
	
	@Column(name="em_cedula_representante", nullable = false)
	private String cedulaRepresentante;
	
	@Column(name="em_razonSocial", nullable = false)
	private String razonSocial;
	
	@Column(name="em_codigo_postal", nullable = false)
	private String codigoPostal;
	
	@Column(name="em_tipo_empresa", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private TipoDeEmpresa tipoEmpresa;

	@Column(name="em_categoria", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private Categoria categoria;
	
	@OneToOne
	private Direccion direccion;
	
	@OneToMany
	private List<Almacen> listaDeAlamacenes;
	
	@OneToMany
	private List<Producto> listaProductos;
	
	
	public void agregarlistaDeAlamacenes(Almacen almacen) {
		this.listaDeAlamacenes.add(almacen);
	}

}
