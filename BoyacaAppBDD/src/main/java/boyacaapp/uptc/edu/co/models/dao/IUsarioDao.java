package boyacaapp.uptc.edu.co.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import boyacaapp.uptc.edu.co.models.entity.Usuario;

public interface IUsarioDao extends JpaRepository<Usuario, Long>{

	Usuario findByEmail(String email);

}
