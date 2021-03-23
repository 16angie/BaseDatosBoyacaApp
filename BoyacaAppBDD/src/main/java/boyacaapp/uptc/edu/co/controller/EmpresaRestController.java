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
import boyacaapp.uptc.edu.co.models.entity.Empresa;
import boyacaapp.uptc.edu.co.models.entity.Imagen;
import boyacaapp.uptc.edu.co.services.IDireccionService;
import boyacaapp.uptc.edu.co.services.IEmpresaService;
import boyacaapp.uptc.edu.co.services.IImagenService;


@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/app")
public class EmpresaRestController {

	@Autowired
	IEmpresaService empresaService;
	

	@Autowired
	IDireccionService direccionservice;
	
	@Autowired
	IImagenService imagenService;
	
	
	
	@GetMapping("/empresas")
	public List<Empresa> index(){
		return empresaService.findAll();
		
	}
	
	@GetMapping("/empresas/{id}")
	public Empresa show(@PathVariable Long id){
		return empresaService.findById(id);
	}
	
	@PostMapping("/empresas/nueva")
	@ResponseStatus(HttpStatus.CREATED)
	public Empresa create(@RequestBody Empresa empresa){
		imagenService.save(empresa.getImagen());
		return empresaService.save(empresa);
	}
	
	@PostMapping("/empresas/actualizarimagenempresa/{id_empresa}")
	@ResponseStatus(HttpStatus.CREATED)
	public Empresa create(@PathVariable Long id_empresa, @RequestBody Imagen imagen){
		Empresa empresa = empresaService.findById(id_empresa);
		imagenService.save(empresa.getImagen());
		empresa.setImagen(imagen);
		return empresaService.save(empresa);
	}
	
	
	
	@PostMapping("/empresas/actualizardireccion/{id_empresa}/{id_direccion}")
	@ResponseStatus(HttpStatus.CREATED)
	public Empresa create(@PathVariable Long id_direccion,@PathVariable Long id_empresa){
		Empresa empresa = empresaService.findById(id_empresa);
		Direccion direcion = direccionservice.findById(id_direccion);
		empresa.setDireccion(direcion);
		return empresaService.save(empresa);
	}

	
	// to -do metodos para las listas
	
	@PostMapping("/empresas/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Empresa update(@RequestBody Empresa empresa, @PathVariable Long id){
		Empresa empresaActual = empresaService.findById(id);
		empresaActual.setCategoria(empresa.getCategoria());
		empresaActual.setCedulaRepresentante(empresa.getCedulaRepresentante());
		empresaActual.setCodigoPostal(empresa.getCodigoPostal());
		empresaActual.setDireccion(empresa.getDireccion());
		empresaActual.setNombreRepresentanteLegal(empresa.getNombreRepresentanteLegal());
		empresaActual.setRazonSocial(empresa.getRazonSocial());
		empresaActual.setTipoEmpresa(empresa.getTipoEmpresa());
		return empresaService.save(empresaActual);
	}
	
	@DeleteMapping("/empresas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		empresaService.delete(id);
	}
}
