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
import boyacaapp.uptc.edu.co.models.entity.EspecificacionProducto;
import boyacaapp.uptc.edu.co.models.entity.Producto;
import boyacaapp.uptc.edu.co.services.IEspecificacionProductoService;
import boyacaapp.uptc.edu.co.services.IProductoService;


@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/especificaciones")
public class EspecificacionProductoRestController {

	@Autowired
	IEspecificacionProductoService especificacionService;
	
	@Autowired
	IProductoService productoService;
	
	@GetMapping("/listar")
	public List<EspecificacionProducto> index(){
		return especificacionService.findAll();
		
	}
	
	@GetMapping("/listarporid/{id}")
	public EspecificacionProducto show(@PathVariable Long id){
		return especificacionService.findById(id);
	}
	
	@PostMapping("/nueva/{id_producto}")
	@ResponseStatus(HttpStatus.CREATED)
	public EspecificacionProducto create(@RequestBody EspecificacionProducto id,@PathVariable Long id_producto){
		Producto p = productoService.findById(id_producto);
		p.getListaDeEspecificaciones().add(id);
		id.setProducto_e(p);
		return especificacionService.save(id);
	}
	
	
	@PostMapping("/nuevasparaproducto/{id_producto}")
	@ResponseStatus(HttpStatus.CREATED)
	public void createsome(@RequestBody List<EspecificacionProducto> lista, @PathVariable Long id_producto){
		Producto p = productoService.findById(id_producto);
		if(p!=null) {
			for (EspecificacionProducto especificacionProducto : lista) {
				p.getListaDeEspecificaciones().add(especificacionProducto);
				especificacionService.save(especificacionProducto);
			}
		}
		productoService.save(p);
	}
	
	
	// arreglar ajajaj urgente 
	
	@PostMapping("/actualizar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public EspecificacionProducto update(@RequestBody EspecificacionProducto especificacion, @PathVariable Long id){
		EspecificacionProducto especificacionActual = especificacionService.findById(id);

		especificacionActual.setIdEspecificacion(especificacion.getIdEspecificacion());
	
		return especificacionService.save(especificacionActual);
	}
	
	@DeleteMapping("/eliminar/{id_especificacion}/id_prodcuto")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id_especificacion,@PathVariable Long id_prodcuto){
		Producto producto = productoService.findById(id_prodcuto);
		EspecificacionProducto especificacion = especificacionService.findById(id_especificacion);
		if(producto.getListaDeEspecificaciones().contains(especificacion)) {
			producto.getListaDeEspecificaciones().remove(especificacion);
		}
		especificacionService.delete(id_especificacion);
	}
}
