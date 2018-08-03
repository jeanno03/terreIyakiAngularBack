package terreIyaki.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import terreIyaki.entity.OrderItem;
import terreIyaki.repository.OrderItemRepository;

@RestController
public class OrderItemController {
	
	private OrderItemRepository orderItemRepository;

	
	public OrderItemController(OrderItemRepository orderItemRepository) {
		super();
		this.orderItemRepository = orderItemRepository;
	}
	
//	
//	//va retourner myOrder avec toutes les infos de la commande
//	//idUser pour retrouver la derniere commande
//	@RequestMapping(value = "/orderItems", method = RequestMethod.POST)
//	@CrossOrigin(origins = "*")
//	public OrderItem save(@RequestBody OrderItem o) {
//
//System.out.println(" idUser idUser idUser : " +idUser);
//		return null;
//}
	
	//createOrderItem

	
	
	
	
	
	
	
	
	
}
