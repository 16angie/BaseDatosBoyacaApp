package boyacaapp.uptc.edu.co.services;

import java.util.List;

import boyacaapp.uptc.edu.co.models.entity.Almacen;

public interface IAlmacenService {
	
	public List<Almacen> findAll();
	public Almacen findById(Long id);
	public Almacen save(Almacen client);
	public void delete(Long id);

}