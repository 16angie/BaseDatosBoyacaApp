package boyacaapp.uptc.edu.co.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import boyacaapp.uptc.edu.co.models.dao.IDireccionDao;
import boyacaapp.uptc.edu.co.models.entity.Direccion;




@Service
public class DireccionServiceImpl implements IDireccionService{
	
	@Autowired
	private IDireccionDao direcciondao;

	@Transactional(readOnly= true)
	public List<Direccion> findAll() {
		
		return (List<Direccion>) direcciondao.findAll();
	}

	@Transactional(readOnly= true)
	public Direccion findById(Long id) {
		
		return direcciondao.findById(id).orElse(null);
	}

	@Transactional
	public Direccion save(Direccion direccion) {
		return direcciondao.save(direccion);
	}

	@Transactional
	public void delete(Long id) {	
		direcciondao.deleteById(id);
	}
}