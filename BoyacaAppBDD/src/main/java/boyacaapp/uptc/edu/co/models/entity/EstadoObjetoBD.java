package boyacaapp.uptc.edu.co.models.entity;

public enum EstadoObjetoBD {

	ACTIVO("Activo"),
	INACTIVO("Inactivo");
	
	private String  estado;
	
	private EstadoObjetoBD(String estado) {
		this.estado =estado;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}