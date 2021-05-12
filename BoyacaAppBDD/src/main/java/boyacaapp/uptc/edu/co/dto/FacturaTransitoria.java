package boyacaapp.uptc.edu.co.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class FacturaTransitoria {
			
		private double valor_total_compra;
		private Long id_cliente;
		private String nombre_dom;
		private String apellido_dom;
		private String numero_telefono_dom;
		private String barrio_dir;
		private String via_dir;
		private String numero_dir; 
		private String datosAdicionales_dir;
		private Long id_ciudad;
		private List<DetalleTransitorio> detalles = new ArrayList<DetalleTransitorio>();
		
		
}
