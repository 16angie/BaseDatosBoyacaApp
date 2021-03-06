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
import boyacaapp.uptc.edu.co.services.ICiudadService;



@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/app")
public class CiudadRestController {

	@Autowired
	ICiudadService ciudadService;
	
	@GetMapping("/ciudades")
	public List<Ciudad> index(){
		return ciudadService.findAll();
		
	}
	
	@GetMapping("/ciudades{id}")
	public Ciudad show(@PathVariable Long id){
		return ciudadService.findById(id);
	}
	
	@PostMapping("/ciudades")
	@ResponseStatus(HttpStatus.CREATED)
	public Ciudad create(@RequestBody Ciudad id){
		return ciudadService.save(id);
	}
	
	@PostMapping("/ciudades/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Ciudad update(@RequestBody Ciudad ciudad, @PathVariable Long id){
		Ciudad ciudadActual = ciudadService.findById(id);
		ciudadActual.setDepartamento(ciudad.getDepartamento());
		ciudadActual.setNombre(ciudad.getNombre());
		return ciudadService.save(ciudadActual);
	}
	
	@DeleteMapping("/ciudades/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		ciudadService.delete(id);
	}
}
