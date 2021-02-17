package boyacaapp.uptc.edu.co.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import boyacaapp.uptc.edu.co.models.dao.IEspecificacionProductoDao;
import boyacaapp.uptc.edu.co.models.entity.EspecificacionProducto;

@Service
public class EspecificacionProductoServiceImpl implements IEspecificacionProductoService{
	
	@Autowired
	private IEspecificacionProductoDao especificaciondao;

	@Override
	@Transactional(readOnly= true)
	public List<EspecificacionProducto> findAll() {
		
		return (List<EspecificacionProducto>) especificaciondao.findAll();
	}

	@Override
	@Transactional(readOnly= true)
	public EspecificacionProducto findById(Long id) {
		
		return especificaciondao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public EspecificacionProducto save(EspecificacionProducto especificacion) {
		return especificaciondao.save(especificacion);
	}

	@Override
	@Transactional
	public void delete(Long id) {	
		especificaciondao.deleteById(id);
	}
}