package boyacaapp.uptc.edu.co.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import boyacaapp.uptc.edu.co.models.dao.ICompraFacturaDao;
import boyacaapp.uptc.edu.co.models.entity.FacturaCompra;


@Service
public class CompraFacturaImpl implements ICompraFacturaService{
	
	@Autowired
	private ICompraFacturaDao facturaCompradao;

	@Transactional(readOnly= true)
	public List<FacturaCompra> findAll() {
		
		return (List<FacturaCompra>) facturaCompradao.findAll();
	}

	@Transactional(readOnly= true)
	public FacturaCompra findById(Long id) {
		
		return facturaCompradao.findById(id).orElse(null);
	}

	@Transactional
	public FacturaCompra save(FacturaCompra facturaCompra) {
		return facturaCompradao.save(facturaCompra);
	}

	@Transactional
	public void delete(Long id) {	
		facturaCompradao.deleteById(id);
	}

	@Override
	public FacturaCompra findByReference(String reference) {
		return facturaCompradao.findByReferenciaDeCompra(reference);
	}

	
}