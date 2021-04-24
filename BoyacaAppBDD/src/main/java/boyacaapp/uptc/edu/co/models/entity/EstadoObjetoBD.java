package boyacaapp.uptc.edu.co.models.entity;

/**
 * Este enumerado define el estado de los objetos almacenados en la base de datos
 * se establece que hay entidades que no se deben eliminar directamente, sino, en su lugar, se establece
 * un estado INACTIVO que permite que no estén disponibles para su manejo por parte de los usuarios.
 * las entidades que adoptan esta medida son: Producto, Almacen, Empresa y Usuario(para inactivar cuenta).
 * @author Diian_Ramirez
 *
 */
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