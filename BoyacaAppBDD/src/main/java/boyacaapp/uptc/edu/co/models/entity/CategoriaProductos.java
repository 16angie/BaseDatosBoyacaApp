package boyacaapp.uptc.edu.co.models.entity;

public enum CategoriaProductos {
	NINO("Niño","Producto"), NINA("Niña","Producto"), HOMBRE("Hombre","Producto"), 
	MUJER("Mujer","Producto"), PARA_TODOS("Para todos","Producto");
	
	
	private String  nombre;
	private String tipo;
	
	private CategoriaProductos(String nombre,String tipo) {
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
