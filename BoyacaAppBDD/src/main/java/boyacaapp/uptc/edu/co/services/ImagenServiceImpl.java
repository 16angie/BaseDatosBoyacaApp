package boyacaapp.uptc.edu.co.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import boyacaapp.uptc.edu.co.models.dao.IImagenDao;
import boyacaapp.uptc.edu.co.models.entity.Imagen;

@Service
public class ImagenServiceImpl implements IImagenService{
	
	@Autowired
	private IImagenDao imagendao;

	@Override
	@Transactional(readOnly= true)
	public List<Imagen> findAll() {
		
		return (List<Imagen>) imagendao.findAll();
	}

	@Override
	@Transactional(readOnly= true)
	public Imagen findById(Long id) {
		
		return imagendao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Imagen save(Imagen imagen) {
		return imagendao.save(imagen);
	}

	@Override
	@Transactional
	public void delete(Long id) {	
		imagendao.deleteById(id);
	}
}