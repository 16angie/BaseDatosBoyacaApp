package boyacaapp.uptc.edu.co.services;

import java.util.List;

import boyacaapp.uptc.edu.co.models.entity.CuentaBancaria;


public interface ICuentaBancariaService {
	
	public List<CuentaBancaria> findAll();
	public CuentaBancaria findById(Long id);
	public CuentaBancaria save(CuentaBancaria client);
	public void delete(Long id);

}