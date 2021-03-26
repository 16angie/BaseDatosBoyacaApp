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
import boyacaapp.uptc.edu.co.models.entity.Ciudad;
import boyacaapp.uptc.edu.co.models.entity.Cliente;
import boyacaapp.uptc.edu.co.models.entity.Direccion;
import boyacaapp.uptc.edu.co.services.ICiudadService;
import boyacaapp.uptc.edu.co.services.IClienteService;
import boyacaapp.uptc.edu.co.services.IDireccionService;
import boyacaapp.uptc.edu.co.services.IImagenService;


@CrossOrigin(origins= {"http://localhost:4200"}) //8082 // en dado caso seria el de angular oki?
@RestController
@RequestMapping("/clientes")
public class ClienteRestController {

	@Autowired()
	IClienteService clienteService;
	
	@Autowired()
	ICiudadService ciudadservice;
	
	@Autowired
	IDireccionService direccionservice;
	
	@Autowired
	
	IImagenService imagenservice;
	
	@GetMapping("/listar")
	public List<Cliente> index(){
		return (List<Cliente>) clienteService.findAll();
		
	}
	
	@GetMapping("/listarporid/{id}")
	public Cliente show(@PathVariable Long id){
		return clienteService.findById(id);
	}
	
	
	
	

	/**
	 * 
	 * @param cliente
	 * @param id_ciudad
	 * @return
	 */
	@PostMapping("/nuevocon/imagen/ciudad/direccion/{id_ciudad}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente create(@RequestBody Cliente cliente,@PathVariable Long id_ciudad){
		Ciudad ciudad = ciudadservice.findById(id_ciudad);
		direccionservice.save(cliente.getDireccionResidencia());
		cliente.getDireccionResidencia().setCiudad(ciudad);
		imagenservice.save(cliente.getImagen());
		return clienteService.save(cliente);
	}
	
	
	@PostMapping("/nuevo")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente createw(@RequestBody Cliente cliente){
		return clienteService.save(cliente);
	}
	
	
	@PostMapping("/actualizardireccion/{id_cliente}/{id_ciudad}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente create(@PathVariable Long id_cliente,@PathVariable Long id_ciudad,@RequestBody Direccion direccion){
		Cliente cliente  = clienteService.findById(id_cliente);
		Ciudad ciudad = ciudadservice.findById(id_ciudad);
		direccion.setCiudad(ciudad);
		direccionservice.save(direccion);
		cliente.setDireccionResidencia(direccion);
		return clienteService.save(cliente);
	}

	
	// TO-DO falta agregar el meetodo de set para la lista de facturas
	
	@PutMapping("/actualizar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente update(@RequestBody Cliente cliente, @PathVariable Long id){
		Cliente clienteActual = clienteService.findById(id);
		clienteActual.setApellido(cliente.getApellido());
		clienteActual.setNumerodecedula(cliente.getNumerodecedula());
		clienteActual.setContrasena(cliente.getContrasena());
		clienteActual.setEmail(cliente.getEmail());
		clienteActual.setNombre(cliente.getNombre());
		clienteActual.setNumeroTelefonico(cliente.getNumeroTelefonico());
		return clienteService.save(clienteActual);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		clienteService.delete(id);
	}
}
