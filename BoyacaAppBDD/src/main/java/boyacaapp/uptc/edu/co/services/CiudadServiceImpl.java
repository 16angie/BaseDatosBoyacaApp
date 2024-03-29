package boyacaapp.uptc.edu.co.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import boyacaapp.uptc.edu.co.models.dao.ICiudadDao;
import boyacaapp.uptc.edu.co.models.entity.Ciudad;

@Service
public class CiudadServiceImpl implements ICiudadService{
	
	@Autowired
	private ICiudadDao ciudaddao;

	@Transactional(readOnly= true)
	public List<Ciudad> findAll() {
		
		return (List<Ciudad>) ciudaddao.findAll();
	}

	@Transactional(readOnly= true)
	public Ciudad findById(Long id) {
		
		return ciudaddao.findById(id).orElse(null);
	}

	@Transactional
	public Ciudad save(Ciudad ciudad) {
		return ciudaddao.save(ciudad);
	}

	@Transactional
	public void delete(Long id) {	
		ciudaddao.deleteById(id);
	}
}