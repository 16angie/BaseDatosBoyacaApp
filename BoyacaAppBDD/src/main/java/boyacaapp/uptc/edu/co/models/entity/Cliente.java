package boyacaapp.uptc.edu.co.models.entity;



import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity 
@Table(name ="cliente")
@Data
@DiscriminatorValue(value="CLIENTE")
public class Cliente extends Usuario{
	
	@OneToOne
	private Direccion direccionResidencia;
	

}
