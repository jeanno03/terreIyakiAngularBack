package terreIyaki.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import terreIyaki.entity.MyOrder;
import terreIyaki.entity.MyUser;
import terreIyaki.entity.OrderType;
import terreIyaki.entity.Statut;
import terreIyaki.entity.TheMessage;
import terreIyaki.repository.MyOrderRepository;
import terreIyaki.repository.MyUserRepository;
import terreIyaki.repository.OrderTypeRepository;
import terreIyaki.repository.StatutRepository;
import terreIyaki.repository.TheMessageRepository;

@Service
public class MyOrderService implements MyOrderServiceInterface{
	
	
	@Autowired
	private MyUserRepository myUserRepository;
	
	@Autowired
	private TheMessageRepository theMessageRepository;
	
	@Autowired
	private MyOrderRepository myOrderRepository;
	
	@Autowired
	private StatutRepository statutRepository;
	
	@Autowired
	private OrderTypeRepository orderTypeRepository;
	
	@Override
	public TheMessage createMyOrderMessage(String name, String email) {
		
		// orderType01 est le orderType choisi cad sur place
		
		OrderType orderType02 = orderTypeRepository.findByName(name);
		
		//myUser01 est l'utilisateur qui effectue l'action
		MyUser myUser01 = myUserRepository.getUserByEmail(email);
		
		//statut01 est le statut de la commande
		Statut statut01 = statutRepository.getStatutByName("commande en cours");
		
		Date aujourdhui = new Date();
		
		MyOrder myOrder01 = new MyOrder(aujourdhui);
		
		myOrder01.setOrderType(orderType02);
		myOrder01.setMyUser(myUser01);
		myOrder01.setStatut(statut01);
		
		myOrderRepository.save(myOrder01);
		
		//message de succès sur place ou a emporter
		if(name.equals("A emporter")) {
			return theMessageRepository.findByNumber(5);	
		}
		else if(name.equals("Sur place")) {
			return theMessageRepository.findByNumber(6);
		}
		return null;
		
	}
	
public MyUser getLastOrderByUser(Long userId){
	myOrderRepository.selectMyOrderByUser(userId);
	
	return null;
}


}
