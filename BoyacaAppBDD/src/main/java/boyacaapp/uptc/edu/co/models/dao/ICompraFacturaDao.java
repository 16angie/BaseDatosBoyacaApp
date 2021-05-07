package boyacaapp.uptc.edu.co.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import boyacaapp.uptc.edu.co.models.entity.FacturaCompra;

public interface ICompraFacturaDao extends JpaRepository<FacturaCompra,Long>{
	
	FacturaCompra findByReferenciaDeCompra(String reference);
	
}