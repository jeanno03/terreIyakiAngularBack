package terreIyaki.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import terreIyaki.entity.Historisation;
import terreIyaki.entity.MyOrder;
import terreIyaki.entity.MyUser;
import terreIyaki.entity.OrderItem;
import terreIyaki.entity.TheMessage;
import terreIyaki.repository.OrderItemRepository;
import terreIyaki.service.MyOrderServiceInterface;
import terreIyaki.tool.LongClass;

@RestController
public class OrderItemController implements Converter<String, Long> {
	
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
	
	//méthode qui va supprimer tous les orders item du menu sélectionné
	@RequestMapping(value = "/deleteComboOrderItem", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	public TheMessage deleteComboOrderItem(@RequestBody List<LongClass> orderItemIdLongClass) {
		
		List <Long>orderItemsIdLong = new ArrayList();
		

		for(LongClass lc : orderItemIdLongClass) {
			Long productIdLong = this.convert(lc);
			orderItemsIdLong.add(productIdLong);
	
		}
		
		return myOrderServiceInterface.deleteComboOrderItem(orderItemsIdLong);
	}
	

	//Méthode ==> une fois le menu sélectionné, le menu est ajouté à la commande ==> méthode createComboOrderItems
	@RequestMapping(value = "/createComboOrderItems/{userId}/{comboId}", method = RequestMethod.PUT)
	@CrossOrigin(origins = "*")
	public TheMessage createComboOrderItems(@PathVariable String userId, @PathVariable String comboId, @RequestBody List<LongClass> productsId) {
		

		
		//je converti tout en Long
		Long userIdLong =  this.convert(userId);	
		Long comboIdLong = this.convert(comboId);
		
		List <Long>productsIdLong = new ArrayList();
		

		
		for(LongClass lc : productsId) {
			Long productIdLong = this.convert(lc);
			productsIdLong.add(productIdLong);
	
		}
		

		//J'envoi en parametre dans la méthode createComboOrderItems
		return myOrderServiceInterface.createComboOrderItems(userIdLong, comboIdLong, productsIdLong);

	}

	//Méthode pour convertir les données String en Long
	//Utilisé dans méthode createComboOrderItems mais peut etre pas nécessaire
	@Override
	public Long convert(String EnterString) {
		// TODO Auto-generated method stub
	       try {
		Long returnLong = Long.valueOf(EnterString);
	            return returnLong;
	        } catch (NumberFormatException e) {
	            return null;
	        }
	}
	
	//Méthode pour convertir les données List<LongClass> en List<Long>
	//dans méthode createComboOrderItems
	public Long convert(LongClass EnterLongClass) {
	       try {
		Long returnLong = Long.valueOf(String.valueOf(EnterLongClass.getIdLong()));
	            return returnLong;
	        } catch (NumberFormatException e) {
	            return null;
	        }
	}
	
	
	
	
	
	
	//méthode test non utlisé ==> exemple d'utilisation de POST
	@RequestMapping(value = "/methodMyUser", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	public void methodTest(@RequestBody MyUser u) {
		MyUser my01 = new MyUser("test","test","test","test");

		System.out.println("ca marche!!!!!!! : "+u.getEmail());
	}
	
	
	
	
}
