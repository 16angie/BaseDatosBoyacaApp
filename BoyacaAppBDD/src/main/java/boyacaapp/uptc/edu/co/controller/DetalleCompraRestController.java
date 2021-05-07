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
import boyacaapp.uptc.edu.co.models.entity.Envio;
import boyacaapp.uptc.edu.co.models.entity.EspecificacionProducto;
import boyacaapp.uptc.edu.co.models.entity.Producto;
import boyacaapp.uptc.edu.co.services.ICompraFacturaService;
import boyacaapp.uptc.edu.co.services.IDetallesCompraService;
import boyacaapp.uptc.edu.co.services.IEnvioService;
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
	IEnvioService envioService;
	
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
	
	@PostMapping("/nuevo/{idEnvio}/{idEspecificacion}/{idProducto}")
	@ResponseStatus(HttpStatus.CREATED)
	public DetalleCompra create(@RequestBody DetalleCompra detalle, @PathVariable Long idEnvio, @PathVariable Long idEspecificacion,@PathVariable Long idProducto){
		Envio envio =  envioService.findById(idEnvio);
		EspecificacionProducto esp = especificacionProductoService.findById(idEspecificacion);
		Producto prod =  productoservice.findById(idProducto);
		detalle.setEnvio_c(envio);
		detalle.setIdEspecificacionElegida(esp);
		detalle.setIdProducto(prod);
		envio.getDetalleCompra().add(detalle);
		envio.setRepresentante_hizo_envio(prod.getAlmacen().getEmpresa().getRepresentante());
		envioService.save(envio);
		return detalleCompraService.save(detalle);
	}
	
	@PostMapping("/actualizar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public DetalleCompra update(@RequestBody DetalleCompra detalleCompra, @PathVariable Long id){
		DetalleCompra detalleCompraActual = detalleCompraService.findById(id);
		return detalleCompraService.save(detalleCompraActual);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id){
		detalleCompraService.delete(id);
	}
}