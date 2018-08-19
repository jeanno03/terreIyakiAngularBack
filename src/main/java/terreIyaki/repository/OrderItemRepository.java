package terreIyaki.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import terreIyaki.entity.OrderItem;
@RepositoryRestResource
@CrossOrigin(origins = "*")
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

	//	public OrderItem save(@RequestBody float price, float tax, String comment, Long idUser);

	//
	//	//on va chercher tous les orderItem de myOrder
	@Query(nativeQuery=true, value = "SELECT * FROM order_item o JOIN my_order m ON o.my_order_id = m.id WHERE m.id =:paramId")
	List<OrderItem> getOrderItemsByIdMyOrder(@Param("paramId")Long myOrderId);
	//	
	//	
	//	
	//	//méthode qui va récupérer la last commande de l'user
	//	@Query(nativeQuery=true, value = "SELECT TOP 1 * FROM my_order m JOIN statut s ON m.statut_id= s.id WHERE m.my_user_id= :paramId AND s.name ='commande en cours' ORDER BY m.id DESC")
	//	MyOrder selectLastMyOrderByUser(@Param("paramId")Long userId);

	OrderItem findById(Long orderItemId);


}
