package boyacaapp.uptc.edu.co.models.entity;

public enum TipoBanco {
	BANCO_AGRARIO("BANCO AGRARIO"),BANCO_AV_VILLAS("BANCO AV VILLAS"),
	BANCO_BBVA_COLOMBIA("BANCO BBVA COLOMBIA S.A."),BANCO_CAJA_SOCIAL("BANCO CAJA SOCIAL"),
	BANCO_COOPERATIVO_COOPCENTRAL("BANCO COOPERATIVO COOPCENTRAL"),BANCO_DAVIVIENDA("BANCO DAVIVIENDA"),
	BANCO_DE_BOGOTA("BANCO_DE_BOGOTA"),BANCO_DE_OCCIDENTE("BANCO DE OCCIDENTE"),
	BANCO_FALABELLA("BANCO FALABELLA"),BANCO_FINANDINA("BANCO FINANDINA"),BANCO_GNB_SUDAMERIS("BANCO GNB SUDAMERIS"),
	BANCO_ITAU("BANCO ITAU"),BANCO_PICHINCHA("BANCO PICHINCHA S.A."),BANCO_POPULAR("BANCO POPULAR"),
	BANCO_PROCREDIT("BANCO PROCREDIT"),BANCO_SANTANDER_COLOMBIA("BANCO SANTANDER COLOMBIA"),
	BANCO_SERFINANZA("BANCO SERFINANZA"),BANCOLOMBIA("BANCOLOMBIA"),BANCOOMEVA("BANCOOMEVA S.A."),
	CFA_COOPERATIVA_FINANCIERA("CFA COOPERATIVA FINANCIERA"),CITIBANK("CITIBANK"),
	CONFIAR_COOPERATIVA_FINANCIERA("CONFIAR COOPERATIVA FINANCIERA"),
	SCOTIABANK_COLPATRIA("SCOTIABANK COLPATRIA");
	
	
	private String nombre;
	
	private TipoBanco(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}