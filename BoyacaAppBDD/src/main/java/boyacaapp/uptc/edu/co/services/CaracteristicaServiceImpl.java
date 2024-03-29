package boyacaapp.uptc.edu.co.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import boyacaapp.uptc.edu.co.models.dao.ICaracteristicaDao;
import boyacaapp.uptc.edu.co.models.entity.Caracteristica;

@Service
public class CaracteristicaServiceImpl implements ICaracteristicaService{
	
	@Autowired
	private ICaracteristicaDao caracteristicadao;

	@Transactional(readOnly= true)
	public List<Caracteristica> findAll() {
		
		return (List<Caracteristica>) caracteristicadao.findAll();
	}

	@Transactional(readOnly= true)
	public Caracteristica findById(Long id) {
		
		return caracteristicadao.findById(id).orElse(null);
	}
	
	@Transactional
	public Caracteristica save(Caracteristica almacen) {
		return caracteristicadao.save(almacen);
	}

	@Transactional
	public void delete(Long id) {	
		caracteristicadao.deleteById(id);
	}
}