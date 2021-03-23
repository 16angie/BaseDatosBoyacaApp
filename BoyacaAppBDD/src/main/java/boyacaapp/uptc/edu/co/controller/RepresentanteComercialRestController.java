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

import boyacaapp.uptc.edu.co.models.entity.Direccion;
import boyacaapp.uptc.edu.co.models.entity.RepresentanteComercial;
import boyacaapp.uptc.edu.co.services.IDireccionService;
import boyacaapp.uptc.edu.co.services.IRepresentanteComercialService;


@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/app")
public class RepresentanteComercialRestController {

	@Autowired
	IRepresentanteComercialService representanteComercialService;
	
	@Autowired
	IDireccionService direccionservice;
	
	@GetMapping("/representantesComerciales")
	public List<RepresentanteComercial> index(){
		return representanteComercialService.findAll();
		
	}
	
	@GetMapping("/representantesComerciales{id}")
	public RepresentanteComercial show(@PathVariable Long id){
		return representanteComercialService.findById(id);
	}
	
	@PostMapping("/representantesComerciales/nuevo")
	@ResponseStatus(HttpStatus.CREATED)
	public RepresentanteComercial create(@RequestBody RepresentanteComercial representantes){
		return representanteComercialService.save(representantes);
	}
	
	@PostMapping("/representantesComerciales/actulizardireccion/{id_representante}/{id_direccion}")
	@ResponseStatus(HttpStatus.CREATED)
	public RepresentanteComercial create(@PathVariable Long id_direccion,@PathVariable Long id_representante){
		RepresentanteComercial representanteComercialActual = representanteComercialService.findById(id_representante);
		Direccion direcion = direccionservice.findById(id_direccion);
		representanteComercialActual.setDireccion_residencia(direcion);
		return representanteComercialService.save(representanteComercialActual);
	}
	
	
	@PostMapping("/representantesComerciales/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public RepresentanteComercial update(@RequestBody RepresentanteComercial representanteComercial, @PathVariable Long id){
		RepresentanteComercial representanteComercialActual = representanteComercialService.findById(id);
		representanteComercialActual.setApellido(representanteComercial.getApellido());
		representanteComercialActual.setEmail(representanteComercial.getEmail());
		representanteComercialActual.setNombre(representanteComercial.getNombre());
		representanteComercialActual.setContrasena(representanteComercial.getContrasena());
		representanteComercialActual.setCuenta(representanteComercial.getCuenta());
		representanteComercialActual.setDireccion_residencia(representanteComercial.getDireccion_residencia());
		representanteComercialActual.setNombreNegocio(representanteComercial.getNombreNegocio());
		representanteComercialActual.setNumero_telefono(representanteComercial.getNumero_telefono());
		representanteComercialActual.setNumerodecedula(representanteComercial.getNumerodecedula());
		return representanteComercialService.save(representanteComercialActual);
	}
	
	@DeleteMapping("/representantesComerciales/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		representanteComercialService.delete(id);
	}
}
