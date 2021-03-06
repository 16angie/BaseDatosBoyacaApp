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

import boyacaapp.uptc.edu.co.models.entity.Cliente;
import boyacaapp.uptc.edu.co.services.IClienteService;


@CrossOrigin(origins= {"http://localhost:4200"}) //8082 // en dado caso seria el de angular oki?
@RestController
@RequestMapping("/app")
public class ClienteRestController {

	@Autowired
	IClienteService clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> index(){
		return clienteService.findAll();
		
	}
	
	@GetMapping("/clientes{id}")
	public Cliente show(@PathVariable Long id){
		return clienteService.findById(id);
	}
	
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente create(@RequestBody Cliente id){
		return clienteService.save(id);
	}

	
	
	// TO-DO falta agregar el meetodo de set para la lista de facturas
	
	@PostMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente update(@RequestBody Cliente cliente, @PathVariable Long id){
		Cliente clienteActual = clienteService.findById(id);
		clienteActual.setApellido(cliente.getApellido());
		clienteActual.setCedula(cliente.getCedula());
		clienteActual.setContrasena(cliente.getContrasena());
		clienteActual.setDireccion_residencia(cliente.getDireccion_residencia());
		clienteActual.setEmail(cliente.getEmail());
		clienteActual.setNombre(cliente.getNombre());
		clienteActual.setNumero_telefono(cliente.getNumero_telefono());
		return clienteService.save(clienteActual);
	}
	
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		clienteService.delete(id);
	}
}
