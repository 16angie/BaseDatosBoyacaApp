package boyacaapp.uptc.edu.co.services;

import java.util.List;

import boyacaapp.uptc.edu.co.models.entity.Departamento;

public interface IDepartamentoService {
	
	public List<Departamento> findAll();
	public Departamento findById(Long id);
	public Departamento save(Departamento client);
	public void delete(Long id);

}