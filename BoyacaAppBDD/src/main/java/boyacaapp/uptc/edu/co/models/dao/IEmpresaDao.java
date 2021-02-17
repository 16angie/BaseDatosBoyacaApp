package boyacaapp.uptc.edu.co.models.dao;

import org.springframework.data.repository.CrudRepository;

import boyacaapp.uptc.edu.co.models.entity.Empresa;

public interface IEmpresaDao extends CrudRepository<Empresa,Long>{
	

}