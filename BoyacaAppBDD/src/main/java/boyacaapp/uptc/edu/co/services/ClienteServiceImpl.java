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

	@Override
	@Transactional(readOnly= true)
	public List<Cliente> findAll() {
		
		return (List<Cliente>) clientedao.findAll();
	}

	@Override
	@Transactional(readOnly= true)
	public Cliente findById(Long id) {
		
		return clientedao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clientedao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {	
		clientedao.deleteById(id);
	}
}