package terreIyaki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import terreIyaki.entity.Statut;

@RepositoryRestResource
@CrossOrigin(origins = "*")
@Repository
public interface StatutRepository extends JpaRepository<Statut, Long>{

	Statut getStatutByName(String name);

	Statut findByNumero(int id);


}
