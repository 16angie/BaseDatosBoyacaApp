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
import boyacaapp.uptc.edu.co.services.IEspecificacionProductoService;


@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/especificaciones")
public class EspecificacionProductoRestController {

	@Autowired
	IEspecificacionProductoService especificacionService;
	
	@GetMapping("/listar")
	public List<EspecificacionProducto> index(){
		return especificacionService.findAll();
		
	}
	
	@GetMapping("/listarporid/{id}")
	public EspecificacionProducto show(@PathVariable Long id){
		return especificacionService.findById(id);
	}
	
	@PostMapping("/nueva")
	@ResponseStatus(HttpStatus.CREATED)
	public EspecificacionProducto create(@RequestBody EspecificacionProducto id){
		return especificacionService.save(id);
	}
	
	@PostMapping("/actualizar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public EspecificacionProducto update(@RequestBody EspecificacionProducto especificacion, @PathVariable Long id){
		EspecificacionProducto especificacionActual = especificacionService.findById(id);
		especificacionActual.setCantidad(especificacion.getCantidad());
		especificacionActual.setIdProducto(especificacion.getIdProducto());
		especificacionActual.setNombre(especificacion.getNombre());
		return especificacionService.save(especificacionActual);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		especificacionService.delete(id);
	}
}
