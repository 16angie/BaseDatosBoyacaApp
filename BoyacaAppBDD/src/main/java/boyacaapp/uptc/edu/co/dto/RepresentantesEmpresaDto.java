package boyacaapp.uptc.edu.co.dto;

import java.util.List;

import boyacaapp.uptc.edu.co.models.entity.Almacen;
import lombok.Data;

@Data
public class RepresentantesEmpresaDto {
		
		private Long idRepresentante;
		private String emailRepresentante;
		private Long idEmpresa;
		private String nombreEmempresa;
		private List<Almacen>listaAlmacenes;
}
