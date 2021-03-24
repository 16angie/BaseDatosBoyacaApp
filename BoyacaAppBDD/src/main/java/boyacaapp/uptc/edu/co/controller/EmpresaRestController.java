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
import boyacaapp.uptc.edu.co.models.entity.Ciudad;
import boyacaapp.uptc.edu.co.models.entity.Direccion;
import boyacaapp.uptc.edu.co.models.entity.Empresa;
import boyacaapp.uptc.edu.co.models.entity.Imagen;
import boyacaapp.uptc.edu.co.models.entity.RepresentanteComercial;
import boyacaapp.uptc.edu.co.services.IAlmacenService;
import boyacaapp.uptc.edu.co.services.ICiudadService;
import boyacaapp.uptc.edu.co.services.IDireccionService;
import boyacaapp.uptc.edu.co.services.IEmpresaService;
import boyacaapp.uptc.edu.co.services.IImagenService;
import boyacaapp.uptc.edu.co.services.IRepresentanteComercialService;


@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/empresas")
public class EmpresaRestController {

	@Autowired
	IEmpresaService empresaService;
	
	
	@Autowired
	IAlmacenService almacenService;

	@Autowired
	IDireccionService direccionservice;
	
	@Autowired
	IImagenService imagenService;
	
	@Autowired
	ICiudadService ciudadservice;
	
	@Autowired
	IRepresentanteComercialService representanteservice;
	
	
	
	@GetMapping("/listar")
	public List<Empresa> index(){
		return empresaService.findAll();
		
	}
	
	@GetMapping("/encontrarid/{id}")
	public Empresa show(@PathVariable Long id){
		return empresaService.findById(id);
	}
	
	
	
	@PostMapping("/crear/nueva/{id_representante}")
	@ResponseStatus(HttpStatus.CREATED)
	public Empresa create(@RequestBody Empresa empresa,@PathVariable Long id_representante){
		RepresentanteComercial representante =representanteservice.findById(id_representante);
		empresa.setRepresentante(representante);
		return empresaService.save(empresa);
	}
	
	
	/**
	 *nueva empresa con imagen incluida  y direccion incluida
	 * **/
	@PostMapping("/nuevacon/direccione/imagen/{id_ciudad}")
	@ResponseStatus(HttpStatus.CREATED)
	public Empresa createe(@RequestBody Empresa empresa,@PathVariable Long id_ciudad){
		Ciudad ciudad = ciudadservice.findById(id_ciudad);
		imagenService.save(empresa.getImagen());
		direccionservice.save(empresa.getDireccion());
		empresa.getDireccion().setCiudad(ciudad);
		return empresaService.save(empresa);
	}
	
	
	@PostMapping("/actualizarimagenempresa/{id_empresa}")
	@ResponseStatus(HttpStatus.CREATED)
	public Empresa create(@PathVariable Long id_empresa, @RequestBody Imagen imagen){
		Empresa empresa = empresaService.findById(id_empresa);
		imagenService.save(empresa.getImagen());
		empresa.setImagen(imagen);
		return empresaService.save(empresa);
	}
	
	
	@PostMapping("/actualizardireccion/{id_empresa}")
	@ResponseStatus(HttpStatus.CREATED)
	public Empresa create(@PathVariable Long id_empresa,@RequestBody Direccion direccion){
		Empresa empresa = empresaService.findById(id_empresa);
		 direccionservice.save(direccion);
		 empresa.setDireccion(direccion);
		return empresaService.save(empresa);
	}

	@PostMapping("/ingresarleunalmacen/{id_empresa}")
	@ResponseStatus(HttpStatus.CREATED)
	public Empresa create(@PathVariable Long id_empresa,@RequestBody Almacen almacen){
		Empresa empresa = empresaService.findById(id_empresa);
		 almacenService.save(almacen);
		 empresa.getListaDeAlamacenes().add(almacen);
		return empresaService.save(empresa);
	}

	
	@PostMapping("/actualizarcompleta/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Empresa update(@RequestBody Empresa empresa, @PathVariable Long id){
		Empresa empresaActual = empresaService.findById(id);
		empresaActual.setCategoria(empresa.getCategoria());
		empresaActual.setCodigoPostal(empresa.getCodigoPostal());
		empresaActual.setDireccion(empresa.getDireccion());
		empresaActual.setRazonSocial(empresa.getRazonSocial());
		empresaActual.setTipoEmpresa(empresa.getTipoEmpresa());
		return empresaService.save(empresaActual);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		empresaService.delete(id);
	}
}
