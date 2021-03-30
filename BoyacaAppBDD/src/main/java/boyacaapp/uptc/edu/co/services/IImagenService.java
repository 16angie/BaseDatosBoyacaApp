package boyacaapp.uptc.edu.co.services;

import java.util.List;

import boyacaapp.uptc.edu.co.models.entity.Imagen;

public interface IImagenService {
	
	public List<Imagen> findAll();
	public Imagen findById(Long id);
	public Imagen save(Imagen imagen);
	public void delete(Long id);
	public Imagen findByNombre(String nombre);

}