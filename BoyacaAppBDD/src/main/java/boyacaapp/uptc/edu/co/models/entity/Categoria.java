package boyacaapp.uptc.edu.co.models.entity;

public enum Categoria {
	
	
	/**
	 * alguanas categorias son para empresas y para productos. 
	 */
	
	NINO("Niño","Producto"), NINA("Niña","Producto"), HOMBRE("Hombre","Producto"), 
	MUJER("Mujer","Producto"), PARA_TODOS("Para todos","Producto"),
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
