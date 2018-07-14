package terreIyaki.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import terreIyaki.entity.Combo;
import terreIyaki.entity.Product;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
@Repository
public interface ComboRepository extends JpaRepository<Combo, Long>{
	
	Combo findByNameIgnoreCase(String name);
	
}
