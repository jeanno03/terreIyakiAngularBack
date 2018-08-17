package terreIyaki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import terreIyaki.entity.OrderType;
import terreIyaki.entity.TheMessage;
import terreIyaki.repository.OrderTypeRepository;
import terreIyaki.service.MyOrderServiceInterface;

@RestController
public class OrderTypeController {

	private OrderTypeRepository orderTypeRepository;

	@Autowired
	private MyOrderServiceInterface myOrderServiceInterface;

	public OrderTypeController(OrderTypeRepository orderTypeRepository) {
		super();
		this.orderTypeRepository = orderTypeRepository;
	}

	//on choisi si on prend a emporter ou sur place
	@RequestMapping(value = "/getOrderTypeByName", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public OrderType getOrderTypeByName(String name) {
		return orderTypeRepository.findByName(name);
	}

	//on ouvre une commande a emporter ou sur place
	@RequestMapping(value = "/selectOrder", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public TheMessage selectOrder(String name, String email) {
		return myOrderServiceInterface.createMyOrderMessage(name, email);		
	}	

	//on ajoute la table a la commande sur place
	@RequestMapping(value = "/chooseTable", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public TheMessage chooseTable(int tableNumber, Long  userId) {
		return myOrderServiceInterface.chooseTable(tableNumber, userId);
	}

	
}
