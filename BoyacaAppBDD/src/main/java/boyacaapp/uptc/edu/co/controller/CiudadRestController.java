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
import boyacaapp.uptc.edu.co.models.entity.Departamento;
import boyacaapp.uptc.edu.co.services.ICiudadService;
import boyacaapp.uptc.edu.co.services.IDepartamentoService;



@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/ciudades")
public class CiudadRestController {

	@Autowired
	ICiudadService ciudadService;
	
	@Autowired
	IDepartamentoService departamentoService;
	
	@GetMapping("/listar")
	public List<Ciudad> index(){
		return ciudadService.findAll();
		
	}
	
	@GetMapping("/listarciudadesid/{id}")
	public Ciudad show(@PathVariable Long id){
		return ciudadService.findById(id);
	}
	
	@PostMapping("/nueva/{id_departamento}")
	@ResponseStatus(HttpStatus.CREATED)
	public Ciudad create(@PathVariable Long id_departamento,@RequestBody Ciudad ciudad){
		Departamento departamento =departamentoService.findById(id_departamento);
		ciudad.setDepartamento(departamento);
		return ciudadService.save(ciudad);
	}
	
	@PostMapping("/actualizar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Ciudad update(@RequestBody Ciudad ciudad, @PathVariable Long id){
		Ciudad ciudadActual = ciudadService.findById(id);
		ciudadActual.setDepartamento(ciudad.getDepartamento());
		ciudadActual.setNombre(ciudad.getNombre());
		return ciudadService.save(ciudadActual);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		ciudadService.delete(id);
	}
}
