package boyacaapp.uptc.edu.co.services;



import java.util.List;

import boyacaapp.uptc.edu.co.models.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();
	public Cliente findById(Long id);
	public Cliente  save(Cliente usuario);
	public void delete(Long id);

}