package boyacaapp.uptc.edu.co.models.dao;

import org.springframework.data.repository.CrudRepository;

import boyacaapp.uptc.edu.co.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente,Long>{
	

}