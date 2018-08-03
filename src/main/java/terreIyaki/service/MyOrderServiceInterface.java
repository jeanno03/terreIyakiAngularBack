package terreIyaki.service;

import java.util.List;

import terreIyaki.entity.MyUser;
import terreIyaki.entity.OrderItem;
import terreIyaki.entity.OrderType;
import terreIyaki.entity.TheMessage;

public interface MyOrderServiceInterface {
	
	public TheMessage createMyOrderMessage(String name, String email);
	
	public TheMessage createOrderItem(Long productId, Long userId);

}
