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

import boyacaapp.uptc.edu.co.models.entity.DetalleCompra;
import boyacaapp.uptc.edu.co.services.IDetallesCompraService;


@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/app")
public class DetalleCompraRestController {

	@Autowired
	IDetallesCompraService detalleCompraService;
	
	@GetMapping("/detallesCompra")
	public List<DetalleCompra> index(){
		return detalleCompraService.findAll();
		
	}
	
	@GetMapping("/detallesCompra{id}")
	public DetalleCompra show(@PathVariable Long id){
		return detalleCompraService.findById(id);
	}
	
	@PostMapping("/detallesCompra")
	@ResponseStatus(HttpStatus.CREATED)
	public DetalleCompra create(@RequestBody DetalleCompra id){
		return detalleCompraService.save(id);
	}
	
	@PostMapping("/detallesCompra/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public DetalleCompra update(@RequestBody DetalleCompra detalleCompra, @PathVariable Long id){
		DetalleCompra detalleCompraActual = detalleCompraService.findById(id);
		detalleCompraActual.setCantidad(detalleCompra.getCantidad());
		detalleCompraActual.setCostoEnvio(detalleCompra.getCostoEnvio());
		detalleCompraActual.setFactura(detalleCompra.getFactura());
		detalleCompraActual.setProducto(detalleCompra.getProducto());
		return detalleCompraService.save(detalleCompraActual);
	}
	
	@DeleteMapping("/detallesCompra/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		detalleCompraService.delete(id);
	}
}
