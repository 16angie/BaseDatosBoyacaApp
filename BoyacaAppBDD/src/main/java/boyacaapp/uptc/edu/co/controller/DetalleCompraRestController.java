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
import boyacaapp.uptc.edu.co.models.entity.EspecificacionProducto;
import boyacaapp.uptc.edu.co.models.entity.FacturaCompra;
import boyacaapp.uptc.edu.co.services.ICompraFacturaService;
import boyacaapp.uptc.edu.co.services.IDetallesCompraService;
import boyacaapp.uptc.edu.co.services.IEspecificacionProductoService;
import boyacaapp.uptc.edu.co.services.IProductoService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/detallecompra")
public class DetalleCompraRestController {

	@Autowired
	IDetallesCompraService detalleCompraService;
	
	@Autowired
	IProductoService productoservice;
	
	@Autowired
	IEspecificacionProductoService especificacionProductoService;
	
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
	
	//aqui falta verificar la cantidad del detalle xq no cuadra
	@PostMapping("/nuevo/{idfactura}/{idespecificacion}")
	@ResponseStatus(HttpStatus.CREATED)
	public DetalleCompra create(@RequestBody DetalleCompra detalle, @PathVariable Long idfactura, @PathVariable Long idespecificacion){
		FacturaCompra compra = facturaService.findById(idfactura);
		EspecificacionProducto esp = especificacionProductoService.findById(idespecificacion);
		detalle.setIdProducto(esp.getProducto_e().getIdProducto());
		detalle.setId_especificacion(idespecificacion);
		detalle.setFactura(compra);
		compra.getDetalleCompra().add(detalle);
		detalle.setFactura(compra);
		return detalleCompraService.save(detalle);
	}
	
	@PostMapping("/actualizar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public DetalleCompra update(@RequestBody DetalleCompra detalleCompra, @PathVariable Long id){
		DetalleCompra detalleCompraActual = detalleCompraService.findById(id);
		detalleCompraActual.setFactura(detalleCompra.getFactura());
		return detalleCompraService.save(detalleCompraActual);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id){
		detalleCompraService.delete(id);
	}
}