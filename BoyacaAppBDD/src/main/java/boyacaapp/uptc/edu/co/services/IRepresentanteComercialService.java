package boyacaapp.uptc.edu.co.services;

import java.util.List;

import boyacaapp.uptc.edu.co.models.entity.RepresentanteComercial;

public interface IRepresentanteComercialService {
	
	public List<RepresentanteComercial> findAll();
	public RepresentanteComercial findById(Long id);
	public RepresentanteComercial save(RepresentanteComercial usuario);
	public void delete(Long id);

}