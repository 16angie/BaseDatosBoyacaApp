package boyacaapp.uptc.edu.co.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import boyacaapp.uptc.edu.co.models.dao.IClienteDao;
import boyacaapp.uptc.edu.co.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService{
	
	@Autowired
	private IClienteDao clientedao;

	@Transactional(readOnly= true)
	public List<Cliente> findAll() {
		return clientedao.findAll();
	}

	@Transactional(readOnly= true)
	public Cliente findById(Long id) {
		return clientedao.findById(id).orElse(null);
	}

	@Transactional
	public Cliente save(Cliente cliente) {
		return clientedao.save(cliente);
	}

	@Transactional
	public void delete(Long id) {	
		clientedao.deleteById(id);
	}
}