package terreIyaki.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import terreIyaki.entity.MyUser;
import terreIyaki.entity.OrderItem;
import terreIyaki.entity.TheMessage;
import terreIyaki.repository.OrderItemRepository;
import terreIyaki.service.MyOrderServiceInterface;
import terreIyaki.tool.LongClass;

@RestController
@CrossOrigin("*")
public class OrderItemController  {

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


	
//	  returnOrderItemByOrder(idOrder: number) {
//		    return this.http.get(this.API + '/myOrders/' + idOrder + '/orderItems').
//		      map((result: any) => {
//		        return result._embedded.orderItems;
//		      })
//		  }
	
	//on veut toutes les ordersItems par l'id de l'order

}
