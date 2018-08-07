package terreIyaki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import terreIyaki.entity.MyOrder;
import terreIyaki.entity.OrderItem;
import terreIyaki.entity.TheMessage;
import terreIyaki.repository.OrderItemRepository;
import terreIyaki.service.MyOrderServiceInterface;

@RestController
public class OrderItemController {
	
	private OrderItemRepository orderItemRepository;

	@Autowired
	private MyOrderServiceInterface myOrderServiceInterface;
	
	
	
	public OrderItemController(OrderItemRepository orderItemRepository) {
		super();
		this.orderItemRepository = orderItemRepository;
	}
	

	@RequestMapping(value = "/getOrderItemsByOrder", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public List<OrderItem> getOrderItemsByOrder(Long myOrderId){
		return orderItemRepository.getOrderItemsByIdMyOrder(myOrderId);
	}
	
	
	@RequestMapping(value = "/incrementeOrderItem", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public TheMessage incrementeOrderItem(Long productId, Long userId) {
		return myOrderServiceInterface.incrementeOrderItem(productId, userId);

	}
	
	@RequestMapping(value = "/decrementeOrderItem", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public TheMessage decrementeOrderItem(Long productId, Long userId) {
		return myOrderServiceInterface.decrementeOrderItem(productId, userId);

	}
	

	@RequestMapping(value = "/deleteOrderItem", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public TheMessage deleteOrderItem(Long productId, Long userId) {
		return myOrderServiceInterface.deleteOrderItem(productId, userId);

	}	
	
	
	
}
