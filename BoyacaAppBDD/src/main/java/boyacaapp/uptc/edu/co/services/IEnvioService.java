package boyacaapp.uptc.edu.co.services;

import java.util.List;

import boyacaapp.uptc.edu.co.models.entity.Envio;

public interface IEnvioService {
	
	public List<Envio> findAll();
	public Envio findById(Long id);
	public Envio save(Envio envio);
	public void delete(Long id);

}