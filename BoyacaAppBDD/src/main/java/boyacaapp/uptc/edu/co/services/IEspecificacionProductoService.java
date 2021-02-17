package boyacaapp.uptc.edu.co.services;

import java.util.List;

import boyacaapp.uptc.edu.co.models.entity.EspecificacionProducto;


public interface IEspecificacionProductoService {
	
	public List<EspecificacionProducto> findAll();
	public EspecificacionProducto findById(Long id);
	public EspecificacionProducto save(EspecificacionProducto especificacion);
	public void delete(Long id);

}