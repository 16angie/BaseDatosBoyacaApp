package boyacaapp.uptc.edu.co.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import boyacaapp.uptc.edu.co.models.entity.Imagen;
import boyacaapp.uptc.edu.co.models.entity.Producto;
import boyacaapp.uptc.edu.co.services.IImagenService;
import boyacaapp.uptc.edu.co.services.IProductoService;


@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/app")
public class ImagenRestController {

	@Autowired
	IImagenService imagenService;
	
	@Autowired
	IProductoService productoService;
	
	
	@GetMapping("/imagenes")
	public List<Imagen> index(){
		return imagenService.findAll();
		
	}
	
	@GetMapping("/imagenes/{id}")
	public Imagen show(@PathVariable Long id){
		return imagenService.findById(id);
	}
	
	@PostMapping("/imagenes/nueva/{id_producto}")
	@ResponseStatus(HttpStatus.CREATED)
	public Imagen create(@RequestBody Imagen imagen, @PathVariable Long id_producto){
		Producto producto = productoService.findById(id_producto);
		producto.getListaImagenes().add(imagen);
		return imagenService.save(imagen);
	}
	
	
	@PostMapping("/imagenes/nueva")
	@ResponseStatus(HttpStatus.CREATED)
	public Imagen create(@RequestBody Imagen imagen){
		return imagenService.save(imagen);
	}
	
	
	@PostMapping("/imagenes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Imagen update(@RequestBody Imagen imagen, @PathVariable Long id){
		Imagen imagenActual = imagenService.findById(id);
		imagenActual.setFile_image(imagen.getFile_image());
		return imagenService.save(imagenActual);
	}
	
	@DeleteMapping("/imagenes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		imagenService.delete(id);
	}
}
