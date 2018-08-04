package terreIyaki.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import terreIyaki.entity.MyOrder;
@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
@Repository
public interface MyOrderRepository extends JpaRepository<MyOrder, Long>{

//	MyOrder selectLastOrderByEmail(Date today, String email);
	
//	MyOrder getMyOrderByMyUser(Long id);
	
	//méthode test
	@Query(value = "SELECT m FROM MyOrder m WHERE m.myUser.id='1'")
	List<MyOrder> selectMyOrderBy1();

	//méthode qui va récupérer toutes les commandes de l'user
	@Query(value = "SELECT m FROM MyOrder m WHERE m.myUser.id= :paramId")
	List<MyOrder> selectMyOrderByUser(@Param("paramId")Long userId);
	
	//méthode qui va récupérer la last commande de l'user
	@Query(nativeQuery=true, value = "SELECT TOP 1 * FROM my_order m JOIN statut s ON m.statut_id= s.id WHERE m.my_user_id= :paramId AND s.name ='commande en cours' ORDER BY m.id DESC")
	MyOrder selectLastMyOrderByUser(@Param("paramId")Long userId);
	
	//interet ==> PageRequest pageRequest
	//méthode qui va récupérer la last commande de l'user
//	@Query(value = "SELECT m FROM MyOrder m WHERE m.myUser.id= :paramId ORDER BY m.id DESC LIMIT 0, 1")
//	List<MyOrder> selectLastMyOrderByUser(@Param("paramId")Long userId, PageRequest pageRequest);
//	
}
