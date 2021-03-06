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

import boyacaapp.uptc.edu.co.models.entity.Domicilio;
import boyacaapp.uptc.edu.co.services.IDomicilioService;


@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/app")
public class DomicilioRestController {

	@Autowired
	IDomicilioService domicilioService;
	
	@GetMapping("/domicilios")
	public List<Domicilio> index(){
		return domicilioService.findAll();
		
	}
	
	@GetMapping("/domicilios{id}")
	public Domicilio show(@PathVariable Long id){
		return domicilioService.findById(id);
	}
	
	@PostMapping("/domicilios")
	@ResponseStatus(HttpStatus.CREATED)
	public Domicilio create(@RequestBody Domicilio id){
		return domicilioService.save(id);
	}
	
	@PostMapping("/domicilios/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Domicilio update(@RequestBody Domicilio domicilio, @PathVariable Long id){
		Domicilio domicilioActual = domicilioService.findById(id);
		domicilioActual.setApellido(domicilio.getApellido());
		domicilioActual.setDireccion(domicilioActual.getDireccion());
		domicilioActual.setNombre(domicilio.getNombre());
		domicilioActual.setNumero_telefono(domicilio.getNumero_telefono());
		return domicilioService.save(domicilioActual);
	}
	
	@DeleteMapping("/domicilios/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		domicilioService.delete(id);
	}
}
