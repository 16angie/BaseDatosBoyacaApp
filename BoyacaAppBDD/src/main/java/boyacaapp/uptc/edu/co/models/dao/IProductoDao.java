package boyacaapp.uptc.edu.co.models.dao;

import org.springframework.data.repository.CrudRepository;

import boyacaapp.uptc.edu.co.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto,Long>{
	

}