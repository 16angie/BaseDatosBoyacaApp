package boyacaapp.uptc.edu.co.services;

import java.util.List;

import boyacaapp.uptc.edu.co.models.entity.DetalleCompra;

public interface IDetallesCompraService {
	
	public List<DetalleCompra> findAll();
	public DetalleCompra findById(Long id);
	public DetalleCompra save(DetalleCompra detalleCompra);
	public void delete(Long id);

}