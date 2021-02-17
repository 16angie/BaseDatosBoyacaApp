package boyacaapp.uptc.edu.co.services;

import java.util.List;

import boyacaapp.uptc.edu.co.models.entity.Domicilio;


public interface IDomicilioService {
	
	public List<Domicilio> findAll();
	public Domicilio findById(Long id);
	public Domicilio save(Domicilio domicilio);
	public void delete(Long id);

}