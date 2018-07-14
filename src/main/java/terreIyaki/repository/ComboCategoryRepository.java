package terreIyaki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import terreIyaki.entity.ComboCategory;
@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
@Repository
public interface ComboCategoryRepository extends JpaRepository<ComboCategory, Long>{

	ComboCategory findById(Long id) ;

}
