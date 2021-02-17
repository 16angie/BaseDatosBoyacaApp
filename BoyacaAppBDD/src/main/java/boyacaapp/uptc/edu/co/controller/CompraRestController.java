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

import boyacaapp.uptc.edu.co.models.entity.FacturaCompra;
import boyacaapp.uptc.edu.co.services.ICompraFacturaService;



@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/app")
public class CompraRestController {

	@Autowired
	ICompraFacturaService compraService;
	
	@GetMapping("/compras")
	public List<FacturaCompra> index(){
		return compraService.findAll();
		
	}
	
	@GetMapping("/compras{id}")
	public FacturaCompra show(@PathVariable Long id){
		return compraService.findById(id);
	}
	
	@PostMapping("/compras")
	@ResponseStatus(HttpStatus.CREATED)
	public FacturaCompra create(@RequestBody FacturaCompra id){
		return compraService.save(id);
	}
	
	
	
	// hacer un metodo para la lista de detalles post????
	@PostMapping("/compras/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public FacturaCompra update(@RequestBody FacturaCompra compra, @PathVariable Long id){
		FacturaCompra compraActual = compraService.findById(id);
		compraActual.setDomicilioCompra(compra.getDomicilioCompra());
		compraActual.setEnvio(compra.getEnvio());
		compraActual.setEstadodelacompra(compra.getEstadodelacompra());
		compraActual.setFecha_compra(compra.getFecha_compra());
		compraActual.setValor_total_compra(compra.getValor_total_compra());
		return compraService.save(compraActual);
	}
	
	@DeleteMapping("/compras/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		compraService.delete(id);
	}
}
