package boyacaapp.uptc.edu.co.models.entity;

public enum EstadoEnvio {

	
	ENVIADO("Enviado"),RECIBIDO("Recibido"),DEVUELTO("Devuelto");
	
	private String nombre;
	
	private EstadoEnvio(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
