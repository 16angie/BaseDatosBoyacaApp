package boyacaapp.uptc.edu.co.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import boyacaapp.uptc.edu.co.models.entity.Almacen;
import boyacaapp.uptc.edu.co.models.entity.Ciudad;
import boyacaapp.uptc.edu.co.models.entity.Direccion;
import boyacaapp.uptc.edu.co.models.entity.Empresa;
import boyacaapp.uptc.edu.co.models.entity.RepresentanteComercial;
import boyacaapp.uptc.edu.co.services.IAlmacenService;
import boyacaapp.uptc.edu.co.services.ICiudadService;
import boyacaapp.uptc.edu.co.services.IDireccionService;
import boyacaapp.uptc.edu.co.services.IEmpresaService;
import boyacaapp.uptc.edu.co.services.IRepresentanteComercialService;

@CrossOrigin(origins= {"http://localhost:4200"}) // este es para comentariar al frontend
@RestController
@RequestMapping("/almacenes")
public class AlmacenRestController {

	@Autowired
	IAlmacenService almacenService;
	
	@Autowired
	IDireccionService direccionservice;
	
	@Autowired
	ICiudadService ciudadservice;
	
	
	@Autowired
	IEmpresaService empresaservice;
	
	@Autowired
	IRepresentanteComercialService representanteService;
	
	@GetMapping("/listar")
	public List<Almacen> index(){
		return almacenService.findAll();
	}
	
	@GetMapping("/listarporid/{id}")
	public Almacen show(@PathVariable Long id){
		return almacenService.findById(id);
	}
	
	@PostMapping("/nuevo/{id_empresa}/{id_ciudad}")
	@ResponseStatus(HttpStatus.CREATED)
	public Almacen create(@PathVariable Long id_empresa,@RequestBody Almacen alamacen,@PathVariable Long id_ciudad){
		Empresa empresa = empresaservice.findById(id_empresa);
		Ciudad ciudad = ciudadservice.findById(id_ciudad);
		alamacen.getDireccion().setCiudad(ciudad);
		direccionservice.save(alamacen.getDireccion());
		alamacen.setEmpresa(empresa);
		return almacenService.save(alamacen);
	}
	
	@PostMapping("/actualizardireccionporid/{id_almacen}/{id_direccion}")
	@ResponseStatus(HttpStatus.OK)
	public Almacen updatedireccion(@PathVariable Long id_direccion,@PathVariable Long id_almacen){
		Almacen almacenActual = almacenService.findById(id_almacen);
		Direccion direccion = direccionservice.findById(id_direccion);
		almacenActual.setDireccion(direccion);
		return almacenService.save(almacenActual);
	}
	
	@PostMapping("/actualizardireccionporbody/{id_almacen}/{id_ciudad}")
	@ResponseStatus(HttpStatus.OK)
	public Almacen updatedireccionbody(@RequestBody Direccion direccion,@PathVariable Long id_almacen, @PathVariable Long id_ciudad){
		Almacen almacenActual = almacenService.findById(id_almacen);
		Direccion direccioneditar = direccionservice.findById(almacenActual.getDireccion().getId_direccion());
		direccioneditar.setBarrio(direccion.getBarrio());
		direccioneditar.setDatosAdicionales(direccion.getDatosAdicionales());
		direccioneditar.setNumero(direccion.getNumero());
		direccioneditar.setCiudad(ciudadservice.findById(id_ciudad));
		direccioneditar.setVia(direccion.getVia());
		direccionservice.save(direccioneditar);
		almacenActual.setDireccion(direccioneditar);
		return almacenService.save(almacenActual);
	}
	
	@GetMapping("/listarporidrepresentante/{id_representante}")
	public List<Almacen> showe(@PathVariable Long id_representante){
		RepresentanteComercial representante = representanteService.findById(id_representante);
		return representante.getEmpresa().getListaDeAlamacenes();
	}
	
	// actualizacion de datos basicos del almacen - NO DIRECCION, eso es otro service
	
	@PutMapping("/actualizardatosbasicos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Almacen update(@RequestBody Almacen almacen, @PathVariable Long id){
		Almacen almacenActual = almacenService.findById(id);
		almacenActual.setCodigo_postal(almacen.getCodigo_postal());
		almacenActual.setEmail(almacen.getEmail());
		almacenActual.setNombrePersonaAcargo(almacen.getNombrePersonaAcargo());
		almacenActual.setTelefono(almacen.getTelefono());
		return almacenService.save(almacenActual);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		almacenService.delete(id);
	}
}
