package boyacaapp.uptc.edu.co.controller;

import java.util.ArrayList;
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
import boyacaapp.uptc.edu.co.models.entity.Envio;
import boyacaapp.uptc.edu.co.models.entity.EstadoEnvio;
import boyacaapp.uptc.edu.co.models.entity.EstadoObjetoBD;
import boyacaapp.uptc.edu.co.models.entity.FacturaCompra;
import boyacaapp.uptc.edu.co.models.entity.RepresentanteComercial;
import boyacaapp.uptc.edu.co.services.IClienteService;
import boyacaapp.uptc.edu.co.services.ICompraFacturaService;
import boyacaapp.uptc.edu.co.services.IDomicilioService;
import boyacaapp.uptc.edu.co.services.IEnvioService;
import boyacaapp.uptc.edu.co.services.IRepresentanteComercialService;


@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/envios")
public class EnvioRestController {

	@Autowired
	IEnvioService envioService;
	
	@Autowired
	IClienteService clienteservice;
	
	@Autowired
	IRepresentanteComercialService repservice;
	
	
	@Autowired
	ICompraFacturaService facturaService;
	
	@Autowired
	IDomicilioService domicilioService;
	
	@GetMapping("/listar")
	public List<Envio> index(){
		return envioService.findAll();
		
	}
	
	@GetMapping("/listarporid/{id}")
	public Envio show(@PathVariable Long id){
		return envioService.findById(id);
	}
	
	
	@GetMapping("/listarporcliente/{idCliente}")
	public List<Envio> shows(@PathVariable Long idCliente) {
		Cliente cliente = clienteservice.findById(idCliente);
		List<Envio> a = envioService.findAll();
		List<Envio> b = new ArrayList<Envio>();
		if (cliente != null && cliente.getEstadoObjeto().equals(EstadoObjetoBD.ACTIVO)) {
			for (Envio envio : a) {

				if (envio.getEstado_envio().equals(EstadoEnvio.EN_PROCESO)
						&& envio.getFacturaCompra().getCliente().getId() == idCliente) {
					b.add(envio);
				}
			}
		}
		return b;
	}
	
	@GetMapping("/listarporepesentante/{idRepresentante}")
	public List<Envio> showa(@PathVariable Long idRepresentante){
		RepresentanteComercial cliente = repservice.findById(idRepresentante);
		List<Envio> a = envioService.findAll();
		List<Envio> b = new ArrayList<Envio>();
		if (cliente != null && cliente.getEstadoObjeto().equals(EstadoObjetoBD.ACTIVO)) {
			for (Envio envio : a) {
				if (envio.getEstado_envio().equals(EstadoEnvio.EN_PROCESO)
						&& envio.getRepresentante_hizo_envio().getId() == cliente.getId()) {
					b.add(envio);
				}
			}
		}
		return b;
	}
	
	/**
	 * 
	 * @param id
	 * @param idRepresentate
	 * @param idFactura
	 * @param idDomicilio
	 * @return
	 */
	@PostMapping("/nuevo/{idRepresentate}/{idFactura}")
	@ResponseStatus(HttpStatus.CREATED)
	public Envio create(@RequestBody Envio id,  @PathVariable Long  idRepresentate,  @PathVariable Long idFactura){
		RepresentanteComercial rep = repservice.findById(idRepresentate);
		FacturaCompra fact = facturaService.findById(idFactura);
		id.setFacturaCompra(fact);
		id.setRepresentante_hizo_envio(rep);
		return envioService.save(id);
	}
	
	@PostMapping("/actualizar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Envio update(@RequestBody Envio envio, @PathVariable Long id){
		Envio envioActual = envioService.findById(id);
		envioActual.setEstado_envio(envio.getEstado_envio());
		envioActual.setFecha_envio(envio.getFecha_envio());
		envioActual.setFecha_relativa_llegada(envio.getFecha_relativa_llegada());
		envioActual.setNumeroGuia(envio.getNumeroGuia());
		envioActual.setRepresentante_hizo_envio(envio.getRepresentante_hizo_envio());
		return envioService.save(envioActual);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		envioService.delete(id);
	}
}
