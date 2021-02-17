package boyacaapp.uptc.edu.co.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import boyacaapp.uptc.edu.co.models.dao.IDepartamentoDao;
import boyacaapp.uptc.edu.co.models.entity.Departamento;


@Service
public class DepartamentoServiceImpl implements IDepartamentoService{
	
	@Autowired
	private IDepartamentoDao departamentodao;

	@Override
	@Transactional(readOnly= true)
	public List<Departamento> findAll() {
		
		return (List<Departamento>) departamentodao.findAll();
	}

	@Override
	@Transactional(readOnly= true)
	public Departamento findById(Long id) {
		
		return departamentodao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Departamento save(Departamento departamento) {
		return departamentodao.save(departamento);
	}

	@Override
	@Transactional
	public void delete(Long id) {	
		departamentodao.deleteById(id);
	}
}