package boyacaapp.uptc.edu.co.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import boyacaapp.uptc.edu.co.MetodosReusables;
import boyacaapp.uptc.edu.co.models.entity.Almacen;
import boyacaapp.uptc.edu.co.models.entity.Cliente;
import boyacaapp.uptc.edu.co.models.entity.Empresa;
import boyacaapp.uptc.edu.co.models.entity.Imagen;
import boyacaapp.uptc.edu.co.models.entity.Producto;
import boyacaapp.uptc.edu.co.models.entity.RepresentanteComercial;
import boyacaapp.uptc.edu.co.services.IAlmacenService;
import boyacaapp.uptc.edu.co.services.IClienteService;
import boyacaapp.uptc.edu.co.services.IEmpresaService;
import boyacaapp.uptc.edu.co.services.IImagenService;
import boyacaapp.uptc.edu.co.services.IProductoService;
import boyacaapp.uptc.edu.co.services.IRepresentanteComercialService;
import boyacaapp.uptc.edu.co.services.IUsuariosService;


@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/imagenes")
public class ImagenRestController {

	@Autowired
	IImagenService imagenService;
	
	@Autowired()
	IClienteService clienteService;
	
	@Autowired
	IProductoService productoService;
	
	@Autowired
	IAlmacenService alamacenservice;
	
	@Autowired
	IEmpresaService empresaService;
	
	@Autowired
	IUsuariosService usuarioService;
	
	@Autowired
	IRepresentanteComercialService representanteComercialService;
	
	
	@GetMapping("/listar")
	public List<Imagen> index(){
		return imagenService.findAll();
	}
	
	
	@GetMapping("/listarporid/{id}")
	public void show(@PathVariable Long id, HttpServletResponse response) throws IOException {
		Imagen imagen = imagenService.findById(id);
		if (imagen != null) {
			byte[] bytes = (byte[]) imagen.getPixel();
			String mime = (String) imagen.getTipo(); // tipo
			response.setHeader("Content-Type", mime);
			response.getOutputStream().write(bytes);
			
		}
		
	}

	
	@PostMapping("/nuevaparaproducto/{id_producto}")
	@ResponseStatus(HttpStatus.CREATED)
	public Imagen create(@RequestPart("imagen") MultipartFile imagen, @PathVariable Long id_producto){
		Producto producto = productoService.findById(id_producto);
		Imagen imagen2 = null; 
		if(!imagen.isEmpty()) {
			imagen2 = new Imagen();
			try {
				byte[] bytesImg = imagen.getBytes();
				imagen2.setNombre(imagen.getOriginalFilename());
				imagen2.setPixel(bytesImg);
				imagen2.setTamano(imagen.getSize());
				imagen2.setTipo(imagen.getContentType());
				producto.getListaImagenes().add(imagen2);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return imagenService.save(imagen2);
	}
	
	
	@PutMapping("/agregar/imagencliente/{id_cliente}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente creates(@PathVariable Long id_cliente,@RequestPart("imagen") MultipartFile imagen){
		Cliente cliente  = clienteService.findById(id_cliente);
		Imagen imagen2 = null; 
		if(!imagen.isEmpty()) {
			imagen2 = new Imagen();
			try {
				byte[] bytesImg = imagen.getBytes();
				imagen2.setNombre(imagen.getOriginalFilename());
				imagen2.setPixel(bytesImg);
				imagen2.setTamano(imagen.getSize());
				imagen2.setTipo(imagen.getContentType());
				cliente.setImagen(imagen2);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		imagenService.save(imagen2);
		return clienteService.save(cliente);
	}
	
	
	@PutMapping("/agregar/imagenrepresentante/{id_representante}")
	@ResponseStatus(HttpStatus.CREATED)
	public RepresentanteComercial creat(@PathVariable Long id_representante,@RequestPart("imagen") MultipartFile imagen){
		RepresentanteComercial representante  = representanteComercialService.findById(id_representante);
		Imagen imagen2 = null; 
		if(!imagen.isEmpty()) {
			imagen2 = new Imagen();
			try {
				byte[] bytesImg = imagen.getBytes();
				imagen2.setNombre(imagen.getOriginalFilename());
				imagen2.setPixel(bytesImg);
				imagen2.setTamano(imagen.getSize());
				imagen2.setTipo(imagen.getContentType());
				representante.setImagen(imagen2);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		imagenService.save(imagen2);
		return representanteComercialService.save(representante);
	}
	
	
	@PutMapping("/agregar/imagenempresa/{id_empresa}")
	@ResponseStatus(HttpStatus.CREATED)
	public Empresa create(@PathVariable Long id_empresa, @RequestPart("imagen") MultipartFile imagen){
		Empresa empresa = empresaService.findById(id_empresa);
		Imagen imagen2 = null; 
		if(!imagen.isEmpty()) {
			imagen2 = new Imagen();
			try {
				byte[] bytesImg = imagen.getBytes();
				imagen2.setNombre(imagen.getOriginalFilename());
				imagen2.setPixel(bytesImg);
				imagen2.setTamano(imagen.getSize());
				imagen2.setTipo(imagen.getContentType());
				empresa.setImagen(imagen2);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		imagenService.save(imagen2);
		return empresaService.save(empresa);
	}
	
	
	@PutMapping(value="/agregar/nuevasimagenesalproducto/{id_producto}/{id_almacen}",
			  consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
			     produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Producto creates(@PathVariable Long id_producto,@PathVariable Long id_almacen,List< MultipartFile> listaImagenes){
		Producto producto = productoService.findById(id_producto);
		Almacen alamacen = alamacenservice.findById(id_almacen);
		for (MultipartFile imagen : listaImagenes) {
			if(!imagen.isEmpty()) {
				Imagen  imagen2 = new Imagen();
				try {
					byte[] bytesImg = imagen.getBytes();
					String nuevonombre =  MetodosReusables.metodorandomimg()+ imagen.getOriginalFilename();
					imagen2.setNombre(nuevonombre);
					imagen2.setPixel(bytesImg);
					imagen2.setTamano(imagen.getSize());
					imagen2.setTipo(imagen.getContentType());
					imagenService.save(imagen2);
					producto.getListaImagenes().add(imagen2);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		producto.setAlmacen(alamacen);
		return productoService.save(producto);
	}
	
	
	@PostMapping("/nueva")
	@ResponseStatus(HttpStatus.CREATED)
	public Imagen create(@RequestBody Imagen imagen){
		return imagenService.save(imagen);
	}
	
	
	@PostMapping("/actualizar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Imagen update(@RequestBody Imagen imagen, @PathVariable Long id){
		Imagen imagenActual = imagenService.findById(id);
		imagenActual.setNombre(imagen.getNombre());
		return imagenService.save(imagenActual);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		imagenService.delete(id);
	}
}
