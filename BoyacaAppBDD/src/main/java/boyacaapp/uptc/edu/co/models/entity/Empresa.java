package boyacaapp.uptc.edu.co.models.entity;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Entity 
@Table(name="empresas")
@Data

/**
* La empresa es aquella que ofrecerá productos en diferentes almacenes o sedes de ella.
* el ESTADO de la emprea define si está activa (o disponible) o si no lo está
*/
public class Empresa  implements Serializable{
		
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_empresa;
	
	@Column(name="em_razonSocial", nullable = false)
	private String razonSocial;
	
	@Column(name="em_codigo_postal", nullable = false)
	private String codigoPostal;
	
	@Column(name="em_nit", nullable = false)
	private String nit;
	
	@Column(name="em_tipo_empresa", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private TipoDeEmpresa tipoEmpresa;

	@Column(name="em_categoria", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private Categoria categoria;
	
	@Column(name ="em_estado",nullable = true)
	@Enumerated(value = EnumType.STRING)
	private EstadoObjetoBD estadoObjeto = EstadoObjetoBD.ACTIVO;
	
	@OneToOne
	private Direccion direccion;
	
	@OneToOne
	private Imagen imagen;
	
	@OneToOne
	//@JoinColumn(name ="id_empresa")
	//@JsonIgnoreProperties("empresa")
	private RepresentanteComercial representante;
	
	@OneToMany(mappedBy = "empresa",fetch = FetchType.LAZY)
	@JsonIgnoreProperties(ignoreUnknown = true, value="empresa")
	private List<Almacen> listaDeAlamacenes = new ArrayList<Almacen>();
}