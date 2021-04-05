package boyacaapp.uptc.edu.co.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import boyacaapp.uptc.edu.co.models.dao.IRepresentanteComercialDao;
import boyacaapp.uptc.edu.co.models.entity.RepresentanteComercial;

@Service
public class RepresentanteComercialServiceImpl implements IRepresentanteComercialService{
	
	@Autowired
	private IRepresentanteComercialDao representanteComercialdao;

	@Transactional(readOnly= true)
	public List<RepresentanteComercial> findAll() {
		return (List<RepresentanteComercial>) representanteComercialdao.findAll();
	}

	@Transactional(readOnly= true)
	public RepresentanteComercial findById(Long id) {
		
		return (RepresentanteComercial) representanteComercialdao.findById(id).orElse(null);
	}

	@Transactional
	public RepresentanteComercial save(RepresentanteComercial representanteComercial) {
		return (RepresentanteComercial) representanteComercialdao.save(representanteComercial);
	}

	@Transactional
	public void delete(Long id) {	
		representanteComercialdao.deleteById(id);
	}
}