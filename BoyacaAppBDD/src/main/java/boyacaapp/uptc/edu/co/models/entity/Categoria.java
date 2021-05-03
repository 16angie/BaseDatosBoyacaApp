package boyacaapp.uptc.edu.co.models.entity;

public enum Categoria {
	
	
	/**
	 * algunas categorias son para empresas y otras son para productos. 
	 * en el constructor se define a que entidad pertenece cada definición de categoría
	 */
	
	BISUTERIA("Bisuteria","Empresa"),ARTESANIAS("Artesanias","Empresa"),
	PRODUCTOS_NATURALES("Productos Naturales","Empresa"),MUEBLES("Muebles","Empresa"),
	TEXTILES("Textiles","Empresa");
	
	
	private String  nombre;
	private String tipo;
	
	private Categoria(String nombre,String tipo) {
		this.nombre =nombre;
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}	
}