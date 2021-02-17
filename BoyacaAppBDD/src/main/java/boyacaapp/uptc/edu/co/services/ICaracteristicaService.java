package boyacaapp.uptc.edu.co.services;

import java.util.List;

import boyacaapp.uptc.edu.co.models.entity.Caracteristica;

public interface ICaracteristicaService {
	
	public List<Caracteristica> findAll();
	public Caracteristica findById(Long id);
	public Caracteristica save(Caracteristica client);
	public void delete(Long id);

}