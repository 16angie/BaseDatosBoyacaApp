package boyacaapp.uptc.edu.co.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import boyacaapp.uptc.edu.co.models.dao.IEnvioDao;
import boyacaapp.uptc.edu.co.models.entity.Envio;

@Service
public class EnvioServiceImpl implements IEnvioService{
	
	@Autowired
	private IEnvioDao enviodao;

	@Transactional(readOnly= true)
	public List<Envio> findAll() {
		
		return (List<Envio>) enviodao.findAll();
	}

	@Transactional(readOnly= true)
	public Envio findById(Long id) {
		
		return enviodao.findById(id).orElse(null);
	}

	@Transactional
	public Envio save(Envio envio) {
		return enviodao.save(envio);
	}

	@Transactional
	public void delete(Long id) {	
		enviodao.deleteById(id);
	}
}