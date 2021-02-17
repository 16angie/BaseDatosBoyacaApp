package boyacaapp.uptc.edu.co.services;

import java.util.List;

import boyacaapp.uptc.edu.co.models.entity.FacturaCompra;

public interface ICompraFacturaService {
	
	public List<FacturaCompra> findAll();
	public FacturaCompra findById(Long id);
	public FacturaCompra save(FacturaCompra client);
	public void delete(Long id);

}