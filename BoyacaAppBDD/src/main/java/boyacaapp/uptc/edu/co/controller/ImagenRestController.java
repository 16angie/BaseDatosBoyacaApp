package boyacaapp.uptc.edu.co.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import boyacaapp.uptc.edu.co.models.entity.Imagen;
import boyacaapp.uptc.edu.co.models.entity.Producto;
import boyacaapp.uptc.edu.co.services.IImagenService;
import boyacaapp.uptc.edu.co.services.IProductoService;


@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/imagenes")
public class ImagenRestController {

	@Autowired
	IImagenService imagenService;
	
	@Autowired
	IProductoService productoService;
	
	
	@GetMapping("/listar")
	public List<Imagen> index(){
		return imagenService.findAll();
		
	}
	
	@GetMapping("/listarporid/{id}")
	public Imagen show(@PathVariable Long id){
		return imagenService.findById(id);
	}
	
	@PostMapping("/nuevaparaproducto/{id_producto}")
	@ResponseStatus(HttpStatus.CREATED)
	public Imagen create(@RequestPart("imagen") MultipartFile imagen, @PathVariable Long id_producto){
		Producto producto = productoService.findById(id_producto);
		Imagen imagen2 = null; 
		if(!imagen.isEmpty()) {
			Path directoriodeimegnes = Paths.get("src//imagenes");
			String rutaAbsoluta = directoriodeimegnes.toFile().getAbsolutePath();
			imagen2 = new Imagen();
			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutacompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Files.write(rutacompleta,bytesImg);
				imagen2.setNombre(imagen.getOriginalFilename());
				producto.getListaImagenes().add(imagen2);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return imagenService.save(imagen2);
	}
	
	
	@PostMapping("/nueva")
	@ResponseStatus(HttpStatus.CREATED)
	public Imagen create(@RequestBody Imagen imagen){
		return imagenService.save(imagen);
	}
	
	
	@PostMapping("/actualizar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Imagen update(@RequestBody Imagen imagen, @PathVariable Long id){
		Imagen imagenActual = imagenService.findById(id);
		imagenActual.setNombre(imagen.getNombre());
		return imagenService.save(imagenActual);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		imagenService.delete(id);
	}
}
