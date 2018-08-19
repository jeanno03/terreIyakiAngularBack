package terreIyaki.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
	


	


}
