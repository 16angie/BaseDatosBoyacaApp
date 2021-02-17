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

import boyacaapp.uptc.edu.co.models.entity.Departamento;
import boyacaapp.uptc.edu.co.services.IDepartamentoService;



@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/app")
public class DepartamentoRestController {

	@Autowired
	IDepartamentoService departamentoService;
	
	@GetMapping("/departamentos")
	public List<Departamento> index(){
		return departamentoService.findAll();
		
	}
	
	@GetMapping("/departamentos{id}")
	public Departamento show(@PathVariable Long id){
		return departamentoService.findById(id);
	}
	
	@PostMapping("/departamentos")
	@ResponseStatus(HttpStatus.CREATED)
	public Departamento create(@RequestBody Departamento id){
		return departamentoService.save(id);
	}

	
	// agregar el metodo de las listas post?
	@PostMapping("/departamentos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Departamento update(@RequestBody Departamento departamento, @PathVariable Long id){
		Departamento departamentoActual = departamentoService.findById(id);
		departamentoActual.setNombre(departamento.getNombre());
		return departamentoService.save(departamentoActual);
	}
	
	@DeleteMapping("/departamentos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		departamentoService.delete(id);
	}
}
