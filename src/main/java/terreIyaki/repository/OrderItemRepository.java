package terreIyaki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import terreIyaki.entity.MyOrder;
import terreIyaki.entity.OrderItem;
@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
	
//	public OrderItem save(@RequestBody float price, float tax, String comment, Long idUser);


}
