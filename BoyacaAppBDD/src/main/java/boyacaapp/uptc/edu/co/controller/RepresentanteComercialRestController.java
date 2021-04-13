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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import boyacaapp.uptc.edu.co.dto.RepresentantesEmpresaDto;
import boyacaapp.uptc.edu.co.models.entity.EstadoObjetoBD;
import boyacaapp.uptc.edu.co.models.entity.RepresentanteComercial;
import boyacaapp.uptc.edu.co.models.entity.Usuario;
import boyacaapp.uptc.edu.co.services.IImagenService;
import boyacaapp.uptc.edu.co.services.IRepresentanteComercialService;


@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/representantescomerciales")
public class RepresentanteComercialRestController {

	@Autowired
	IRepresentanteComercialService representanteComercialService;
	
	
	@Autowired
	IImagenService imagenservice;
	
	
	@GetMapping("/listar")
	public List<RepresentanteComercial> index(){
		return representanteComercialService.findAll();
		
	}
	
	@GetMapping("/listarid/{id}")
	public Usuario show(@PathVariable Long id){
		return representanteComercialService.findById(id);
	}
	
	@GetMapping("/listarPorIdEmpresa/{id_representante}")
	public RepresentantesEmpresaDto showe(@PathVariable Long id_representante){
		RepresentantesEmpresaDto rep = new RepresentantesEmpresaDto();
		RepresentanteComercial representante = representanteComercialService.findById(id_representante);
		rep.setIdRepresentante(representante.getId());
		rep.setEmailRepresentante(representante.getEmail());
		rep.setIdEmpresa(representante.getEmpresa().getId_empresa());
		rep.setNombreEmempresa(representante.getEmpresa().getRazonSocial()); // es el nombre de la empresa
		rep.setListaAlmacenes(representante.getEmpresa().getListaDeAlamacenes());
		return rep;
	}
	
	@PostMapping("/nuevo")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario create(@RequestBody RepresentanteComercial representantes){
		return representanteComercialService.save(representantes);
	}
	
	
	@PostMapping("/actualizar/{id}")
	@ResponseStatus(HttpStatus.OK)
	public RepresentanteComercial update(@RequestBody RepresentanteComercial representanteComercial, @PathVariable Long id){
		RepresentanteComercial representanteComercialActual = representanteComercialService.findById(id);
		representanteComercialActual.setApellido(representanteComercial.getApellido());
		representanteComercialActual.setEmail(representanteComercial.getEmail());
		representanteComercialActual.setNombre(representanteComercial.getNombre());
		//representanteComercialActual.setContrasena(representanteComercial.getContrasena());
		//representanteComercialActual.setCuenta(representanteComercial.getCuenta());
		representanteComercialActual.setNombreNegocio(representanteComercial.getNombreNegocio());
		representanteComercialActual.setNumeroTelefonico(representanteComercial.getNumeroTelefonico());
		representanteComercialActual.setNumerodecedula(representanteComercial.getNumerodecedula());
		return representanteComercialService.save(representanteComercialActual);
	}
	
	@PostMapping("/actualizarpassword/{id}")
	@ResponseStatus(HttpStatus.OK)
	public RepresentanteComercial updatecontrasena(@RequestParam(value = "contrasena") String contrasena, @PathVariable Long id){
		RepresentanteComercial representanteComercialActual = representanteComercialService.findById(id);
		representanteComercialActual.setContrasena(contrasena);
		return representanteComercialService.save(representanteComercialActual);
	}
	
	//TODO servicio para actualizar la cuenta solita!!!!!!
	
	@DeleteMapping("/borrar/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id){
		RepresentanteComercial representanteComercialActual = representanteComercialService.findById(id);
		representanteComercialActual.setEstadoObjeto(EstadoObjetoBD.INACTIVO);
		representanteComercialService.save(representanteComercialActual);
		//representanteComercialService.delete(id);
	}
}