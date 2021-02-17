package boyacaapp.uptc.edu.co.models.entity;

public enum EstadoCompra {
	EN_PROCESO("En Proceso"),ACEPTADA("Aceptada"),RECHAZADA("Rechazada");
	
	private String nombre;
	private EstadoCompra(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
