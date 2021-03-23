package boyacaapp.uptc.edu.co.controller;

import java.util.ArrayList;
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
import boyacaapp.uptc.edu.co.models.entity.Empresa;
import boyacaapp.uptc.edu.co.models.entity.Imagen;
import boyacaapp.uptc.edu.co.models.entity.Producto;
import boyacaapp.uptc.edu.co.services.IEmpresaService;
import boyacaapp.uptc.edu.co.services.IImagenService;
import boyacaapp.uptc.edu.co.services.IProductoService;


@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/app")
public class ProductoRestController {

	@Autowired
	IProductoService productoService;
	
	@Autowired
	IEmpresaService empresaService;
	
	@Autowired
	IImagenService imagenesservice;
	
	@GetMapping("/productos")
	public List<Producto> index(){
		return productoService.findAll();
	}
	
	@GetMapping("/productos{id}")
	public Producto show(@PathVariable Long id){
		return productoService.findById(id);
	}
	
	@PostMapping("/productos/nuevasIamgenes/{id_producto}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto create(@PathVariable Long id_producto,@RequestBody ArrayList<String> listaImagenes){
		Producto producto = productoService.findById(id_producto);
		for (String url : listaImagenes) {
			Imagen imagen = new Imagen();
			imagen.setFile_image(url);
			imagenesservice.save(imagen);
			producto.getListaImagenes().add(imagen);
		}
		return productoService.save(producto);
	}
	

	
	
	@PostMapping("/productos/nuevo/{id_empresa}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto create(@PathVariable Long id_empresa,@RequestBody Producto producto){
		Empresa empresa = empresaService.findById(id_empresa);
		producto.setEmpresaP(empresa);
		return productoService.save(producto);
	}
	
	
	@PostMapping("/productos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto update(@RequestBody Producto producto, @PathVariable Long id){
		Producto productoActual = productoService.findById(id);
		productoActual.setGenero(producto.getGenero());
		productoActual.setNombre(producto.getNombre());
		productoActual.setPeso(producto.getPeso());
		productoActual.setPrecio(producto.getPrecio());
		productoActual.setPrecio_envio(producto.getPrecio_envio());
		return productoService.save(productoActual);
	}
	
	@DeleteMapping("/productos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		productoService.delete(id);
	}
}
