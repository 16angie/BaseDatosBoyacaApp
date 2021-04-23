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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import boyacaapp.uptc.edu.co.dto.ProductoBasicoDto;
import boyacaapp.uptc.edu.co.models.entity.Almacen;
import boyacaapp.uptc.edu.co.models.entity.EstadoObjetoBD;
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
	
	@GetMapping("/listarcompleto")
	public List<Producto> index(){
	
		List<Producto> listAux = new ArrayList<Producto>();
		for (Producto pr : productoService.findAll()) {
			if (pr.getEstadoObjeto().equals(EstadoObjetoBD.ACTIVO)) {
				listAux.add(pr);
			}
		}
		return listAux;
	}
	
	
	@GetMapping("/listarhome")
	public List<ProductoBasicoDto> indexdto(){
		List<ProductoBasicoDto> listAux = new ArrayList<ProductoBasicoDto>();
		for (Producto pr : productoService.findAll()) {
			if (pr.getEstadoObjeto().equals(EstadoObjetoBD.ACTIVO)) {
				ProductoBasicoDto productoBasico = new ProductoBasicoDto();
				productoBasico.setIdProducto(pr.getIdProducto());
				productoBasico.setNombre(pr.getNombre());
				productoBasico.setPrecio(pr.getPrecio());
				productoBasico.setStock(pr.getStock_total());
				productoBasico.setGenero(pr.getGenero().getNombre());
				if (!pr .getListaImagenes().isEmpty()) {
					productoBasico.setImagenIlustrativa(pr .getListaImagenes().get(0));
				}
				listAux.add(productoBasico);
			}
		}
		return listAux;
	}
	
	
	@GetMapping("/listartodos")
	public List<Producto> indextodos(){
		return productoService.findAll();
	}

	
	@GetMapping("/encontrarporidhome/{id}")
	public ProductoBasicoDto showhome(@PathVariable Long id){
		Producto producto = productoService.findById(id);
		ProductoBasicoDto productoBasico = new ProductoBasicoDto();
		productoBasico.setIdProducto(producto.getIdProducto());
		productoBasico.setNombre(producto.getNombre());
		productoBasico.setPrecio(producto.getPrecio());
		productoBasico.setStock(producto.getStock_total());
		productoBasico.setGenero(producto.getGenero().getNombre());
		if (!producto.getListaImagenes().isEmpty()) {
			productoBasico.setImagenIlustrativa(producto.getListaImagenes().get(0));
		}
		return productoBasico;
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
				productoBasico.setStock(producto.getStock_total());
				productoBasico.setGenero(producto.getGenero().getNombre());
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
				productoBasico.setStock(producto.getStock_total());
				productoBasico.setGenero(producto.getGenero().getNombre());
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
				productoBasico.setStock(producto.getStock_total());
				productoBasico.setGenero(producto.getGenero().getNombre());
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
		Producto producto = productoService.findById(id);
		producto.setEstadoObjeto(EstadoObjetoBD.valueOf("INACTIVO"));
		productoService.save(producto);
		//return producto.getEstadoObjeto().getEstado();
		//productoService.delete(id);
	}
}