package terreIyaki.service;

import java.sql.SQLException;
import java.util.List;

import terreIyaki.entity.MyOrder;
import terreIyaki.entity.MyUser;
import terreIyaki.entity.OrderItem;
import terreIyaki.entity.OrderType;
import terreIyaki.entity.TheMessage;

public interface MyOrderServiceInterface {
	
	public TheMessage createMyOrderMessage(String name, String email);
	
	public TheMessage createOrderItem(Long productId, Long userId);
	
	public TheMessage chooseTable(int tableNumber, Long  userId);
	
	public TheMessage incrementeOrderItem(Long productId, Long userId);
	
	public TheMessage decrementeOrderItem(Long productId, Long userId);
	
	public TheMessage deleteOrderItem(Long productId, Long userId); 
	
	public TheMessage createComboOrderItems(Long userId, Long comboId, List <Long> productsId);

	public TheMessage deleteComboOrderItem(List <Long> orderItemIds) ;
	
	public TheMessage confirmOrder(Long userId);
	
	public TheMessage deleteOrder(Long userId);
	
	public MyOrder getLastOrderByUser(Long userId) throws SQLException;
}
