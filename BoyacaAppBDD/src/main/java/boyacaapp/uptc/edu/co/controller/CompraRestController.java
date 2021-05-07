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
import boyacaapp.uptc.edu.co.dto.ReferenciaDto;
import boyacaapp.uptc.edu.co.dto.RespuestaPasarela;
import boyacaapp.uptc.edu.co.models.entity.Cliente;
import boyacaapp.uptc.edu.co.models.entity.FacturaCompra;
import boyacaapp.uptc.edu.co.services.IClienteService;
import boyacaapp.uptc.edu.co.services.ICompraFacturaService;
import boyacaapp.uptc.edu.co.services.IDetallesCompraService;
import boyacaapp.uptc.edu.co.services.IDomicilioService;
import boyacaapp.uptc.edu.co.utils.MetodosReusables;

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
	
	
	@GetMapping("/generarReferenciaUnica")
	public ReferenciaDto generate(){
		ReferenciaDto ref = new ReferenciaDto();
		ref.setReference(MetodosReusables.metodorandomimg()+"-"+System.currentTimeMillis());
		return ref;
	}
	
	
	
	/**
	 * //el domicilio y la lista de detalles se cargan por en body del request
	//Revisar el envio si es por aparte o si va incluido en la compra aqui*/
	
	@PostMapping("/revisardatospasarella")
	@ResponseStatus(HttpStatus.CREATED)
	public RespuestaPasarela create(@RequestBody RespuestaPasarela respuesta ){
		RespuestaPasarela res = respuesta;
			return res;
	}
	
	/**
	 * //el domicilio y la lista de detalles se cargan por en body del request
	//Revisar el envio si es por aparte o si va incluido en la compra aqui
	 * @param compra
	 * @param idcliente
	 * @return
	 */
	@PostMapping("/nueva/{idcliente}")
	@ResponseStatus(HttpStatus.CREATED)
	public FacturaCompra crear(@RequestBody FacturaCompra compra,@PathVariable Long idcliente){
			Cliente cliente = clienteService.findById(idcliente);
			compra.setCliente(cliente);
			compraService.save(compra);
			return compra;
	}
	
	/**
	 * 
	 * @param compra
	 * @param id
	 * @return
	 */
	@PostMapping("/actualizar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public FacturaCompra update(@RequestBody FacturaCompra compra, @PathVariable Long id){
		FacturaCompra compraActual = compraService.findById(id);
		compraActual.setEstadodelacompra(compra.getEstadodelacompra());
		compraActual.setFecha_compra(compra.getFecha_compra());
		compraActual.setValor_total_compra(compra.getValor_total_compra());
		return compraService.save(compraActual);
	}
	
	
	/**
	 * // las compras no se pueden borrar solo ver y quedanen e historial
	 * @param id
	 */
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		
	}
}