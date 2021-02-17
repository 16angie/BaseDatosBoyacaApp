package boyacaapp.uptc.edu.co.models.entity;

public enum ESTADO_ENVIO {

	
	ENVIADO("Enviado"),RECIBIDO("Recibido"),DEVUELTO("Devuelto");
	
	private String nombre;
	
	private ESTADO_ENVIO(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
