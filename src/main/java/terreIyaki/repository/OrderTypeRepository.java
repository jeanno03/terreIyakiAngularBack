package terreIyaki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import terreIyaki.entity.OrderType;
@RepositoryRestResource
@CrossOrigin(origins = "*")
@Repository
public interface OrderTypeRepository extends JpaRepository<OrderType, Long>{

	OrderType findByName(String name);

}
