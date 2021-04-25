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
import boyacaapp.uptc.edu.co.models.entity.FacturaCompra;
import boyacaapp.uptc.edu.co.services.IClienteService;
import boyacaapp.uptc.edu.co.services.ICompraFacturaService;
import boyacaapp.uptc.edu.co.services.IDetallesCompraService;
import boyacaapp.uptc.edu.co.services.IDomicilioService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/compras")
public class CompraRestController {

	@Autowired
	ICompraFacturaService compraService;
	
	@Autowired
	IClienteService clienteService;
	
	@Autowired
	IDomicilioService domicilioService;
	
	@Autowired
	IDetallesCompraService detalleService;
	
	@GetMapping("/listar")
	public List<FacturaCompra> index(){
		return compraService.findAll();	
	}
	
	@GetMapping("/listarcomprasporid/{id}")
	public FacturaCompra show(@PathVariable Long id){
		return compraService.findById(id);
	}
	
	//el domicilio y la lista de detalles se cargan por en body del request
	//Revisar el envio si es por aparte o si va incluido en la compra aqui
	@PostMapping("/nueva/{idcliente}")
	@ResponseStatus(HttpStatus.CREATED)
	public FacturaCompra create(@RequestBody FacturaCompra compra, @PathVariable Long idcliente){
			Cliente cliente = clienteService.findById(idcliente);
			compra.setCliente(cliente);
			compra.generarReferencia();
			compraService.save(compra);
			return compra;
		
	}
	
	// hacer un metodo para la lista de detalles post????
	@PostMapping("/actualizar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public FacturaCompra update(@RequestBody FacturaCompra compra, @PathVariable Long id){
		FacturaCompra compraActual = compraService.findById(id);
		compraActual.setDomicilioCompra(compra.getDomicilioCompra());
		compraActual.setEnvio(compra.getEnvio());
		compraActual.setEstadodelacompra(compra.getEstadodelacompra());
		compraActual.setFecha_compra(compra.getFecha_compra());
		compraActual.setValor_total_compra(compra.getValor_total_compra());
		compraActual.setReferenciaDeCompra(compraActual.getReferenciaDeCompra());
		return compraService.save(compraActual);
	}
	
	// las compras no se pueden borrar solo ver y quedanen e historial
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		//compraService.delete(id); 
	}
}