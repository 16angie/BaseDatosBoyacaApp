package boyacaapp.uptc.edu.co.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import boyacaapp.uptc.edu.co.dto.ProductoBasicoDto;
import boyacaapp.uptc.edu.co.models.entity.Almacen;
import boyacaapp.uptc.edu.co.models.entity.Producto;
import boyacaapp.uptc.edu.co.services.IAlmacenService;
import boyacaapp.uptc.edu.co.services.IImagenService;
import boyacaapp.uptc.edu.co.services.IProductoService;


@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/productos")
public class ProductoRestController {

	@Autowired
	IProductoService productoService;
	
	@Autowired
	IAlmacenService alamacenservice;
	
	@Autowired
	IImagenService imagenesservice;
	
	@GetMapping("/listar")
	public List<Producto> index(){
		return productoService.findAll();
	}
	
	@GetMapping("/encontrarporid/{id}")
	public Producto show(@PathVariable Long id){
		return productoService.findById(id);
	}
	
	@GetMapping("/encontrarporcriterio")
	public ArrayList<ProductoBasicoDto> filterByQuery(@RequestParam(value = "criterio") String criterio){
		ArrayList<ProductoBasicoDto> found = new ArrayList<ProductoBasicoDto>();
		for (Producto producto : productoService.findAll()) {
			if (producto.getNombre().toLowerCase().contains(criterio.toLowerCase()) || 
					producto.getNombre().toLowerCase().equals(criterio.toLowerCase())) {
				ProductoBasicoDto productoBasico = new ProductoBasicoDto();
				productoBasico.setIdProducto(producto.getIdProducto());
				productoBasico.setNombre(producto.getNombre());
				productoBasico.setPrecio(producto.getPrecio());
				if (!producto.getListaImagenes().isEmpty()) {
					productoBasico.setImagenIlustrativa(producto.getListaImagenes().get(0));
				}
				found.add(productoBasico);
			}
		}
		return found;
	}
	
	@GetMapping("/encontrarporcategoria")
	public ArrayList<ProductoBasicoDto> filterByCategoria(@RequestParam(value = "categoria") String categoria){
		ArrayList<ProductoBasicoDto> found = new ArrayList<ProductoBasicoDto>();
		for (Producto producto : productoService.findAll()) {
			if (producto.getGenero().getNombre().toLowerCase().equals(categoria.toLowerCase())) {
				ProductoBasicoDto productoBasico = new ProductoBasicoDto();
				productoBasico.setIdProducto(producto.getIdProducto());
				productoBasico.setNombre(producto.getNombre());
				productoBasico.setPrecio(producto.getPrecio());
				if (!producto.getListaImagenes().isEmpty()) {
					productoBasico.setImagenIlustrativa(producto.getListaImagenes().get(0));
				}
				found.add(productoBasico);
			}
		}
		return found;
	}
	
	@GetMapping("/encontrarporvariosfactores")
	public ArrayList<ProductoBasicoDto> filterByTwo(@RequestParam(value = "categoria") String categoria, @RequestParam(value = "criterio") String criterio){
		ArrayList<ProductoBasicoDto> found = new ArrayList<ProductoBasicoDto>();
		for (Producto producto : productoService.findAll()) {
			if (producto.getNombre().toLowerCase().contains(criterio.toLowerCase()) || 
					producto.getNombre().equals(criterio.toLowerCase()) || 
					producto.getGenero().getNombre().toLowerCase().equals(categoria.toLowerCase())
					) {
				ProductoBasicoDto productoBasico = new ProductoBasicoDto();
				productoBasico.setIdProducto(producto.getIdProducto());
				productoBasico.setNombre(producto.getNombre());
				productoBasico.setPrecio(producto.getPrecio());
				if (!producto.getListaImagenes().isEmpty()) {
					productoBasico.setImagenIlustrativa(producto.getListaImagenes().get(0));
				}
				found.add(productoBasico);
			}
		}
		return found;
	}
	
	
	@PostMapping("/nuevo/{id_alamacen}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto create(@PathVariable Long id_alamacen,@RequestBody Producto producto){
		Almacen alamacen = alamacenservice.findById(id_alamacen);
		producto.setAlmacen(alamacen);
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
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id){
		productoService.delete(id);
	}
}
