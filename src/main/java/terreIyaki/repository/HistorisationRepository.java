package terreIyaki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import terreIyaki.entity.Historisation;
@RepositoryRestResource
@CrossOrigin(origins = "*")
@Repository
public interface HistorisationRepository extends JpaRepository<Historisation, Long>{

	Historisation findByMyOrderId(Long myOrderId);

}
