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

import boyacaapp.uptc.edu.co.models.entity.Ciudad;
import boyacaapp.uptc.edu.co.models.entity.Direccion;
import boyacaapp.uptc.edu.co.services.ICiudadService;
import boyacaapp.uptc.edu.co.services.IDireccionService;



@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/direcciones")
public class DireccionRestController {

	@Autowired
	IDireccionService direccionService;
	
	@Autowired
	ICiudadService ciudadService;
	
	@GetMapping("/listar")
	public List<Direccion> index(){
		return direccionService.findAll();
		
	}
	
	@GetMapping("/listarporid/{id}")
	public Direccion show(@PathVariable Long id){
		return direccionService.findById(id);
	}
	
	@PostMapping("/nueva/{id_ciudad}")
	@ResponseStatus(HttpStatus.CREATED)
	public Direccion create(@RequestBody Direccion direccion,@PathVariable Long id_ciudad){
		Ciudad ciudad = ciudadService.findById(id_ciudad);
		direccion.setCiudad(ciudad);
		return direccionService.save(direccion);
	}
	
	@PostMapping("/actualizar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Direccion update(@RequestBody Direccion direccion, @PathVariable Long id){
		Direccion direccionActual = direccionService.findById(id);
		direccionActual.setBarrio(direccion.getBarrio());
		direccionActual.setCiudad(direccion.getCiudad());
		direccionActual.setDatosAdicionales(direccion.getDatosAdicionales());
		direccionActual.setNumero(direccion.getNumero());
		direccionActual.setVia(direccion.getVia());
		return direccionService.save(direccionActual);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		direccionService.delete(id);
	}
}
