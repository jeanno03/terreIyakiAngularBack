package terreIyaki.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import terreIyaki.entity.MyOrder;
import terreIyaki.repository.MyOrderRepository;
import terreIyaki.service.MyOrderServiceInterface;

@RestController
@CrossOrigin("*")
public class MyOrderController {

	private MyOrderRepository myOrderRepository;

	@Autowired
	MyOrderServiceInterface myOrderServiceInterface;

	public MyOrderController(MyOrderRepository myOrderRepository) {
		super();
		this.myOrderRepository = myOrderRepository;
	}


	//test only
	@RequestMapping(value = "/selectMyOrderBy1", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public List<MyOrder> selectByDate(){
		return myOrderRepository.selectMyOrderBy1();
	}

	@RequestMapping(value = "/selectMyOrderByUser", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public List<MyOrder> selectMyOrderByUser(Long userId){
		return myOrderRepository.selectMyOrderByUser(userId);
	}

	//test de récupérer la derniere commande par objet et non par sql
	@RequestMapping(value = "/selectLastMyOrderByUser", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public MyOrder selectLastMyOrderByUser(Long userId) throws SQLException{
		try{

			//			return myOrderRepository.selectLastMyOrderByUser(userId);
			return myOrderServiceInterface.getLastOrderByUser(userId);
		}catch(NullPointerException ex) {
			System.out.println(ex);
		}
		return null;

	}


	//toutes les commandes de l'utilisateur
	@RequestMapping(value = "/getListOrderByMyUserId", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public List<MyOrder> getListOrderByMyUserId(Long id, int page, int size) throws SQLException{

		try {
//			int page=0;
//			int size=5;
			Sort sort = new Sort(new Order(Direction.DESC, "id"));
			Pageable pageable = new PageRequest(page, size, sort); 

			return myOrderRepository.findByMyUserId(id, pageable) ;

		}catch(NullPointerException ex) {
			System.out.println(ex);
			return null;
		}catch(NoSuchElementException ex) {
			System.out.println(ex);
			return null;		
		}
	}


}
