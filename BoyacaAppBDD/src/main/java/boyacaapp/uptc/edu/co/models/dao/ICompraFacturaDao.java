package boyacaapp.uptc.edu.co.models.dao;

import org.springframework.data.repository.CrudRepository;

import boyacaapp.uptc.edu.co.models.entity.FacturaCompra;

public interface ICompraFacturaDao extends CrudRepository<FacturaCompra,Long>{
	

}