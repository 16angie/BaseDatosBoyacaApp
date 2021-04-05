package boyacaapp.uptc.edu.co.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import boyacaapp.uptc.edu.co.models.dao.IDomicilioDao;
import boyacaapp.uptc.edu.co.models.entity.Domicilio;




@Service
public class DomicilioServiceImpl implements IDomicilioService{
	
	@Autowired
	private IDomicilioDao domiciliodao;

	@Transactional(readOnly= true)
	public List<Domicilio> findAll() {
		
		return (List<Domicilio>) domiciliodao.findAll();
	}

	@Transactional(readOnly= true)
	public Domicilio findById(Long id) {
		
		return domiciliodao.findById(id).orElse(null);
	}

	@Transactional
	public Domicilio save(Domicilio domicilio) {
		return domiciliodao.save(domicilio);
	}

	@Transactional
	public void delete(Long id) {	
		domiciliodao.deleteById(id);
	}
}