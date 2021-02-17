package boyacaapp.uptc.edu.co.services;

import java.util.List;

import boyacaapp.uptc.edu.co.models.entity.Direccion;


public interface IDireccionService {
	
	public List<Direccion> findAll();
	public Direccion findById(Long id);
	public Direccion save(Direccion direccion);
	public void delete(Long id);

}