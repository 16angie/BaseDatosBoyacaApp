package boyacaapp.uptc.edu.co.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import boyacaapp.uptc.edu.co.models.dao.IEmpresaDao;
import boyacaapp.uptc.edu.co.models.entity.Empresa;

@Service
public class EmpresaServiceImpl implements IEmpresaService{
	
	@Autowired
	private IEmpresaDao empresadao;

	@Override
	@Transactional(readOnly= true)
	public List<Empresa> findAll() {
		
		return (List<Empresa>) empresadao.findAll();
	}

	@Override
	@Transactional(readOnly= true)
	public Empresa findById(Long id) {
		
		return empresadao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Empresa save(Empresa empresa) {
		return empresadao.save(empresa);
	}

	@Override
	@Transactional
	public void delete(Long id) {	
		empresadao.deleteById(id);
	}
}