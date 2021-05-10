package boyacaapp.uptc.edu.co.controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
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

import boyacaapp.uptc.edu.co.dto.DetalleTransitorio;
import boyacaapp.uptc.edu.co.dto.FacturaTransitoria;
import boyacaapp.uptc.edu.co.dto.ReferenciaDto;
import boyacaapp.uptc.edu.co.dto.RespuestaPasarela;
import boyacaapp.uptc.edu.co.models.entity.Ciudad;
import boyacaapp.uptc.edu.co.models.entity.Cliente;
import boyacaapp.uptc.edu.co.models.entity.DetalleCompra;
import boyacaapp.uptc.edu.co.models.entity.Direccion;
import boyacaapp.uptc.edu.co.models.entity.Domicilio;
import boyacaapp.uptc.edu.co.models.entity.Envio;
import boyacaapp.uptc.edu.co.models.entity.EspecificacionProducto;
import boyacaapp.uptc.edu.co.models.entity.EstadoCompra;
import boyacaapp.uptc.edu.co.models.entity.FacturaCompra;
import boyacaapp.uptc.edu.co.models.entity.Producto;
import boyacaapp.uptc.edu.co.models.entity.RepresentanteComercial;
import boyacaapp.uptc.edu.co.services.ICiudadService;
import boyacaapp.uptc.edu.co.services.IClienteService;
import boyacaapp.uptc.edu.co.services.ICompraFacturaService;
import boyacaapp.uptc.edu.co.services.IDetallesCompraService;
import boyacaapp.uptc.edu.co.services.IDomicilioService;
import boyacaapp.uptc.edu.co.services.IEspecificacionProductoService;
import boyacaapp.uptc.edu.co.services.IProductoService;
import boyacaapp.uptc.edu.co.services.IRepresentanteComercialService;
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
	IEspecificacionProductoService especificacionService;
	
	@Autowired
	IProductoService productoService;
	
	@Autowired
	ICiudadService ciudadService;
	
	@Autowired
	IDomicilioService domicilioService;
	
	@Autowired
	IDetallesCompraService detalleService;
	
	
	@Autowired
	IRepresentanteComercialService representanteService;
	
	@GetMapping("/listar")
	public List<FacturaCompra> index(){
		return compraService.findAll();	
	}
	
	@GetMapping("/listarcomprasporid/{id}")
	public FacturaCompra show(@PathVariable Long id){
		return compraService.findById(id);
	}
	
	/**
	 * 
	 * @param facturat hace la de cosas !! 
	 * @return
	 */
	@PostMapping("/generarReferenciaUnica")
	public ReferenciaDto generate(@RequestBody FacturaTransitoria facturat){
		ReferenciaDto ref = new ReferenciaDto();
		ref.setReference(MetodosReusables.metodorandomimg()+"-"+System.currentTimeMillis());
		HashMap<Long ,List<DetalleCompra> > representantes = new HashMap<>();
		for (DetalleTransitorio detallet : facturat.getDetalles()) {
			DetalleCompra detallec = new DetalleCompra();
			detallec.setCantidad(detallet.getCantidad());
			EspecificacionProducto esp = especificacionService.findById(detallet.getIdEspecificacion());
			detallec.setIdEspecificacionElegida( esp);
			Producto pro = productoService.findById(detallet.getIdProducto());
			detallec.setIdProducto(pro);
			Long idRep = pro.getAlmacen().getEmpresa().getRepresentante().getId();
			if (representantes.containsKey(idRep)) {
				representantes.get(idRep).add(detallec);
			}else {
				List<DetalleCompra> list = new ArrayList<DetalleCompra>();
				list.add(detallec);
				representantes.put(idRep, list);
			}
		}
		
		Domicilio dom = new Domicilio();
		dom.setNombre(facturat.getNombre_dom());
		dom.setApellido(facturat.getApellido_dom());
		dom.setNumero_telefono(facturat.getNumero_telefono_dom());
		
		Direccion direccion = new Direccion();
		
		direccion.setBarrio(facturat.getBarrio_dir());
		direccion.setNumero(facturat.getNumero_dir());
		direccion.setVia(facturat.getVia_dir());
		direccion.setDatosAdicionales(facturat.getDatosAdicionales_dir());
		
		Ciudad ciudad = ciudadService.findById(facturat.getId_ciudad());
		
		direccion.setCiudad(ciudad);
		dom.setDireccion(direccion);
		
		List<Envio> envios = new ArrayList<Envio>();
		for (Long id : representantes.keySet() ) {
			Envio envio = new Envio();
			RepresentanteComercial rep = representanteService.findById(id);
			envio.setRepresentante_hizo_envio(rep);
			envio.setDomicilio(dom);
			envio.setDetalleCompra(representantes.get(id));
			envios.add(envio);
		}
		Cliente cli = clienteService.findById(facturat.getId_cliente());
		FacturaCompra fac = new FacturaCompra();
		fac.setCliente(cli);
		fac.setReferenciaDeCompra(ref.getReference());
		fac.setValor_total_compra(facturat.getValor_total_compra());
		fac.setEnvios(envios);
		compraService.save(fac);
		return ref;
	}
	
	/**
	 * //el domicilio y la lista de detalles se cargan por en body del request
	//Revisar el envio si es por aparte o si va incluido en la compra aqui*/
	
	
	@PostMapping("/revisardatospasarela")
	@ResponseStatus(HttpStatus.OK)
	public RespuestaPasarela create(@RequestBody RespuestaPasarela respuesta){
		String status = respuesta.getData().getTransaction().getStatus();
		String referencia = respuesta.getData().getTransaction().getReference();
		FacturaCompra fact = compraService.findByReference(referencia);
		fact.setFecha_compra(GregorianCalendar.getInstance());
		if (status.equals("APPROVED")) {
			fact.setEstadodelacompra(EstadoCompra.ACEPTADA);
		}else {
			fact.setEstadodelacompra(EstadoCompra.RECHAZADA);
		}
		return respuesta;
	}
	

	@GetMapping("/revisarestadosdelapasarela/{idfactura}")
	@ResponseStatus(HttpStatus.CREATED)
	public void revisar(@RequestBody String estado,@PathVariable Long idfactura){
		FacturaCompra fact = compraService.findById(idfactura);
		if (estado.equals("APROBADO")) {
			fact.setEstadodelacompra(EstadoCompra.ACEPTADA);
		}else {
			fact.setEstadodelacompra(EstadoCompra.RECHAZADA);
		}
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