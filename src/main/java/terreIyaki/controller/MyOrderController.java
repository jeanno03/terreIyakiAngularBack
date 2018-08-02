package terreIyaki.controller;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import terreIyaki.entity.MyOrder;
import terreIyaki.repository.MyOrderRepository;

@RestController
public class MyOrderController {
	
	private MyOrderRepository myOrderRepository;

	public MyOrderController(MyOrderRepository myOrderRepository) {
		super();
		this.myOrderRepository = myOrderRepository;
	}
	
//	@RequestMapping(value = "/selectLastOrderByEmail", method = RequestMethod.GET)
//	@CrossOrigin(origins = "*")
//	public MyOrder selectLastOrderByEmail(Date today, String email) {
//		return myOrderRepository.selectLastOrderByEmail(today, email);
//	}
//	
	
	
	
//	http://localhost:8080/myUsers/3/myOrders
//	On va chercher toutes les commandes de l'utlisateurs
	
//	@RequestMapping(value="/getMyOrderByMyUserId)
//	public MyOrder getMyOrderByMyUserId(Long id) {
//		return myOrderRepository.getMyOrderByMyUser(id);
//	}
	
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
	
	
	@RequestMapping(value = "/selectLastMyOrderByUser", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public MyOrder selectLastMyOrderByUser(Long userId){
		return myOrderRepository.selectLastMyOrderByUser(userId);
	}
	
//	@RequestMapping(value = "/selectLastMyOrderByUser", method = RequestMethod.GET)
//	@CrossOrigin(origins = "*")
//	public List<MyOrder> selectLastMyOrderByUser(Long userId){
//		return myOrderRepository.selectLastMyOrderByUser(userId, new PageRequest(0,1));
//	}
	
}
