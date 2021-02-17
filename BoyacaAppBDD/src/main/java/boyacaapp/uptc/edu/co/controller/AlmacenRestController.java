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

import boyacaapp.uptc.edu.co.models.entity.Almacen;

@CrossOrigin(origins= {"http://localhost:4200"}) // este es para comentariar al frontend
@RestController
@RequestMapping("/app")
public class AlmacenRestController {

	@Autowired
	boyacaapp.uptc.edu.co.services.IAlmacenService almacenService;
	
	@GetMapping("/almacenes")
	public List<Almacen> index(){
		return almacenService.findAll();
		
	}
	
	@GetMapping("/almacenes{id}")
	public Almacen show(@PathVariable Long id){
		return almacenService.findById(id);
	}
	
	@PostMapping("/almacenes")
	@ResponseStatus(HttpStatus.CREATED)
	public Almacen create(@RequestBody Almacen id){
		return almacenService.save(id);
	}
	
	@PostMapping("/almacenes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Almacen update(@RequestBody Almacen almacen, @PathVariable Long id){
		Almacen almacenActual = almacenService.findById(id);
		almacenActual.setCodigo_postal(almacen.getCodigo_postal());
		almacenActual.setDireccion(almacen.getDireccion());
		almacenActual.setEmail(almacen.getEmail());
		almacenActual.setNombrePersonaAcargo(almacen.getNombrePersonaAcargo());
		almacenActual.setTelefono(almacen.getTelefono());
		return almacenService.save(almacenActual);
	}
	
	@DeleteMapping("/almacenes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		almacenService.delete(id);
	}
}
