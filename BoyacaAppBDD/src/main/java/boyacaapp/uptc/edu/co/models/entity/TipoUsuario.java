package boyacaapp.uptc.edu.co.models.entity;

public enum TipoUsuario {
	
	CLIENTE("Cliente"),REPRESENTANTECOMERCIAL("Representante Comercial");
	
	private String nombre;

	TipoUsuario(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
