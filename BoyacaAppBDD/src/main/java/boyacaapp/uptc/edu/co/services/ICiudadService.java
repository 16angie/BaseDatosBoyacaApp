package boyacaapp.uptc.edu.co.services;

import java.util.List;

import boyacaapp.uptc.edu.co.models.entity.Ciudad;

public interface ICiudadService {
	
	public List<Ciudad> findAll();
	public Ciudad findById(Long id);
	public Ciudad save(Ciudad ciudad);
	public void delete(Long id);

}