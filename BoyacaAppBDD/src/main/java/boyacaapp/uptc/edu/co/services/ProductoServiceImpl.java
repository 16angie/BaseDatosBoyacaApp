package boyacaapp.uptc.edu.co.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import boyacaapp.uptc.edu.co.models.dao.IProductoDao;
import boyacaapp.uptc.edu.co.models.entity.Producto;


@Service
public class ProductoServiceImpl implements IProductoService{
	
	@Autowired
	private IProductoDao productodao;

	@Override
	@Transactional(readOnly= true)
	public List<Producto> findAll() {
		
		return (List<Producto>) productodao.findAll();
	}

	@Override
	@Transactional(readOnly= true)
	public Producto findById(Long id) {
		
		return productodao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		return productodao.save(producto);
	}

	@Override
	@Transactional
	public void delete(Long id) {	
		productodao.deleteById(id);
	}
}