package boyacaapp.uptc.edu.co.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import boyacaapp.uptc.edu.co.models.dao.ICuentaBancariaDao;
import boyacaapp.uptc.edu.co.models.entity.CuentaBancaria;


@Service
public class CuentaBancariaServiceImpl implements ICuentaBancariaService{
	
	@Autowired
	private ICuentaBancariaDao cuentabancariadao;

	@Override
	@Transactional(readOnly= true)
	public List<CuentaBancaria> findAll() {
		
		return (List<CuentaBancaria>) cuentabancariadao.findAll();
	}

	@Override
	@Transactional(readOnly= true)
	public CuentaBancaria findById(Long id) {
		
		return cuentabancariadao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public CuentaBancaria save(CuentaBancaria cuentaBancaria) {
		return cuentabancariadao.save(cuentaBancaria);
	}

	@Override
	@Transactional
	public void delete(Long id) {	
		cuentabancariadao.deleteById(id);
	}
}