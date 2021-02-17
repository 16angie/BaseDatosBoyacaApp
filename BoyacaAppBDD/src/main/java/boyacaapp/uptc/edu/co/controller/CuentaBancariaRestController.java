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

import boyacaapp.uptc.edu.co.models.entity.CuentaBancaria;
import boyacaapp.uptc.edu.co.services.ICuentaBancariaService;



@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/app")
public class CuentaBancariaRestController {

	@Autowired
	ICuentaBancariaService cuentaBancariaService;
	
	@GetMapping("/cuentasBancarias")
	public List<CuentaBancaria> index(){
		return cuentaBancariaService.findAll();
		
	}
	
	@GetMapping("/cuentasBancarias{id}")
	public CuentaBancaria show(@PathVariable Long id){
		return cuentaBancariaService.findById(id);
	}
	
	@PostMapping("/cuentasBancarias")
	@ResponseStatus(HttpStatus.CREATED)
	public CuentaBancaria create(@RequestBody CuentaBancaria id){
		return cuentaBancariaService.save(id);
	}
	
	@PostMapping("/cuentasBancarias/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public CuentaBancaria update(@RequestBody CuentaBancaria cuentaBancaria, @PathVariable Long id){
		CuentaBancaria cuentaBancariaActual = cuentaBancariaService.findById(id);
		cuentaBancariaActual.setAccountIdPayu(cuentaBancaria.getAccountIdPayu());
		cuentaBancariaActual.setMechantIdPayu(cuentaBancaria.getMechantIdPayu());
		cuentaBancariaActual.setNitTitular(cuentaBancaria.getNitTitular());
		cuentaBancariaActual.setNombreTitular(cuentaBancaria.getNombreTitular());
		cuentaBancariaActual.setNumeroCuenta(cuentaBancaria.getNumeroCuenta());
		cuentaBancariaActual.setTipoBanco(cuentaBancaria.getTipoBanco());
		cuentaBancariaActual.setTipoCuenta(cuentaBancaria.getTipoCuenta());
		return cuentaBancariaService.save(cuentaBancariaActual);
	}
	
	@DeleteMapping("/cuentasBancarias/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		cuentaBancariaService.delete(id);
	}
}
