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
import boyacaapp.uptc.edu.co.models.entity.FacturaCompra;
import boyacaapp.uptc.edu.co.services.ICompraFacturaService;
import boyacaapp.uptc.edu.co.services.IDetallesCompraService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/detallecompra")
public class DetalleCompraRestController {

	@Autowired
	IDetallesCompraService detalleCompraService;
	
	@Autowired
	ICompraFacturaService facturaService;
	
	@GetMapping("/listar")
	public List<DetalleCompra> index(){
		return detalleCompraService.findAll();
	}
	
	@GetMapping("/listarporid/{id}")
	public DetalleCompra show(@PathVariable Long id){
		return detalleCompraService.findById(id);
	}
	
	@PostMapping("/nuevo/{idfactura}")
	@ResponseStatus(HttpStatus.CREATED)
	public DetalleCompra create(@RequestBody DetalleCompra detalle, @PathVariable Long idfactura){
		FacturaCompra compra = facturaService.findById(idfactura);
		detalle.setFactura(compra);
		return detalleCompraService.save(detalle);
	}
	
	@PostMapping("/actualizar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public DetalleCompra update(@RequestBody DetalleCompra detalleCompra, @PathVariable Long id){
		DetalleCompra detalleCompraActual = detalleCompraService.findById(id);
		detalleCompraActual.setCantidad(detalleCompra.getCantidad());
		detalleCompraActual.setCostoEnvio(detalleCompra.getCostoEnvio());
		detalleCompraActual.setFactura(detalleCompra.getFactura());
		detalleCompraActual.setProducto(detalleCompra.getProducto());
		return detalleCompraService.save(detalleCompraActual);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id){
		detalleCompraService.delete(id);
	}
}