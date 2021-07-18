package boyacaapp.uptc.edu.co.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import boyacaapp.uptc.edu.co.models.dao.IAlmacenDao;
import boyacaapp.uptc.edu.co.models.entity.Almacen;

@Service
public class AlmacenServiceImpl implements IAlmacenService{
	
	@Autowired
	private IAlmacenDao almacendao;
	
	

	@Transactional(readOnly= true)
	public List<Almacen> findAll() {
		return (List<Almacen>) almacendao.findAll();
	}

	@Transactional(readOnly= true)
	public Almacen findById(Long id) {
		return almacendao.findById(id).orElse(null);
	}

	@Transactional
	public Almacen save(Almacen almacen) {
		return almacendao.save(almacen);
	}

	@Transactional
	public void delete(Long id) {	
		almacendao.deleteById(id);
	}
}