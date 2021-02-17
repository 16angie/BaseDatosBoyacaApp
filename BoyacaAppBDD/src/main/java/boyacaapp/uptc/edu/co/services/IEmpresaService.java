package boyacaapp.uptc.edu.co.services;

import java.util.List;

import boyacaapp.uptc.edu.co.models.entity.Empresa;

public interface IEmpresaService {
	
	public List<Empresa> findAll();
	public Empresa findById(Long id);
	public Empresa save(Empresa empresa);
	public void delete(Long id);

}