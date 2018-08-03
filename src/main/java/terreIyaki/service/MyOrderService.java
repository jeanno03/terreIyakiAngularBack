package terreIyaki.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import terreIyaki.entity.MyOrder;
import terreIyaki.entity.MyUser;
import terreIyaki.entity.OrderItem;
import terreIyaki.entity.OrderType;
import terreIyaki.entity.Product;
import terreIyaki.entity.Statut;
import terreIyaki.entity.TheMessage;
import terreIyaki.repository.MyOrderRepository;
import terreIyaki.repository.MyUserRepository;
import terreIyaki.repository.OrderItemRepository;
import terreIyaki.repository.OrderTypeRepository;
import terreIyaki.repository.ProductRepository;
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
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	
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
	
//méthode qui va créé orderItem, récupérer le last myOrder
//elle va rechercher statut.numero=7
//elle va faire un orderItem set ProductId, statutId=9
	//va retourner la list de tous les ordersItems de la commande
	@Override
public TheMessage createOrderItem(Long productId, Long userId){
	
	//je cherche le produit via son id
	Product p01 = productRepository.findById(productId);
	
	//je cherche le statut numéro 7
	Statut s01 = statutRepository.findByNumero(7);
	
	//je cherche la derniere commande
	MyOrder m01 = myOrderRepository.selectLastMyOrderByUser(userId);
	
	String comment = "produit ajouté";
	
	OrderItem o01 = new OrderItem(p01.getPrice(), p01.getTax(),comment);
	o01.setProduct(p01);
	o01.setStatut(s01);
	o01.setMyOrder(m01);
	
	orderItemRepository.save(o01);
	
	return theMessageRepository.findByNumber(7);
	

}
	
}
