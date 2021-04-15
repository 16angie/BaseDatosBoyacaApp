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

import boyacaapp.uptc.edu.co.models.entity.Caracteristica;
import boyacaapp.uptc.edu.co.models.entity.Producto;
import boyacaapp.uptc.edu.co.services.ICaracteristicaService;
import boyacaapp.uptc.edu.co.services.IProductoService;



@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/caracteristicas")
public class CaracteristicaRestController {

	@Autowired
	ICaracteristicaService caracteristicaService;
	
	@Autowired
	IProductoService productoService;
	
	@GetMapping("/listar")
	public List<Caracteristica> index(){
		return caracteristicaService.findAll();
		
	}
	
	@GetMapping("/listarporid/{id}")
	public Caracteristica show(@PathVariable Long id){
		return caracteristicaService.findById(id);
	}
	
	@PostMapping("/nueva/{id_producto}")
	@ResponseStatus(HttpStatus.CREATED)
	public Caracteristica create(@PathVariable Long id_producto,@RequestBody Caracteristica caracteristica){
		Producto producto = productoService.findById(id_producto);
		caracteristica.setProducto(producto);
		producto.getListaCaracteristicas().add(caracteristica);
		return caracteristicaService.save(caracteristica);
	}
	
	@PostMapping("/muchasnuevas/{id_producto}")
	@ResponseStatus(HttpStatus.CREATED)
	public void createsome(@RequestBody List<Caracteristica> lista, @PathVariable Long id_producto){
		Producto p = productoService.findById(id_producto);
		if(p!=null) {
			for (Caracteristica caracteristica : lista) {
				p.getListaCaracteristicas().add(caracteristica);
				caracteristicaService.save(caracteristica);
			}
		}
		productoService.save(p);
	}
	
	@PostMapping("/actualizar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Caracteristica update(@RequestBody Caracteristica caracteristica, @PathVariable Long id){
		Caracteristica caracteristicaActual = caracteristicaService.findById(id);
		caracteristicaActual.setDescripcion(caracteristica.getDescripcion());
		caracteristicaActual.setNombre(caracteristica.getNombre());
		return caracteristicaService.save(caracteristicaActual);
	}
	
	@DeleteMapping("/eliminar/{id_caracterstica}/{id_prodcuto}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id, @PathVariable Long id_prodcuto){
		Producto producto = productoService.findById(id_prodcuto);
		Caracteristica caracteristicaActual = caracteristicaService.findById(id);
	// por hacer //
		caracteristicaService.delete(id);
	}
}
