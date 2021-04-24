package boyacaapp.uptc.edu.co.models.entity;

/**
 * se define el tipo de una empresa según los tipos presentes en Colombia.
 * @author Diian_Ramirez
 */
public enum TipoDeEmpresa {
	
	SAS("S.A.S"),LTD("LTA"),SA("S.A"),SCA("S.C.A"),CIA("&CIA"),
	SOCIEDAD_COLECTIVA("SOCIEDAD COLECTICA "),S_EN_C(" S.EN.C ");
	
	private String nombre;

	TipoDeEmpresa(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}