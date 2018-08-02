package terreIyaki.service;

import terreIyaki.entity.MyUser;
import terreIyaki.entity.OrderType;
import terreIyaki.entity.TheMessage;

public interface MyOrderServiceInterface {
	
	public TheMessage createMyOrderMessage(String name, String email);
	
	public MyUser getLastOrderByUser(Long userId);

}
