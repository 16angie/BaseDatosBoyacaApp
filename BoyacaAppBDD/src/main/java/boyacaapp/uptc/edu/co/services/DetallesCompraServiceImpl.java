package boyacaapp.uptc.edu.co.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import boyacaapp.uptc.edu.co.models.dao.IDetallesCompraDao;
import boyacaapp.uptc.edu.co.models.entity.DetalleCompra;



@Service
public class DetallesCompraServiceImpl implements IDetallesCompraService{
	
	@Autowired
	private IDetallesCompraDao detallecompradao;

	@Transactional(readOnly= true)
	public List<DetalleCompra> findAll() {
		
		return (List<DetalleCompra>) detallecompradao.findAll();
	}

	@Transactional(readOnly= true)
	public DetalleCompra findById(Long id) {
		
		return detallecompradao.findById(id).orElse(null);
	}

	@Transactional
	public DetalleCompra save(DetalleCompra detallecompra) {
		return detallecompradao.save(detallecompra);
	}

	@Transactional
	public void delete(Long id) {	
		detallecompradao.deleteById(id);
	}
}