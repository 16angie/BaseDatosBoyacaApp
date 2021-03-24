package boyacaapp.uptc.edu.co.models.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import boyacaapp.uptc.edu.co.models.entity.RepresentanteComercial;

@Repository
public interface IRepresentanteComercialDao extends JpaRepository<RepresentanteComercial, Long> {
	

}