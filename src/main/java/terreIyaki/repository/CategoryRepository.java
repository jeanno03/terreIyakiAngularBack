package terreIyaki.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import terreIyaki.entity.Category;

@RepositoryRestResource
@CrossOrigin(origins = "*")
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByNameIgnoreCase(String name);

}
