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
@CrossOrigin("*")
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



	
}
