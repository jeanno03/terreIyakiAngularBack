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
import terreIyaki.entity.TheMessage;
import terreIyaki.repository.TheMessageRepository;
import terreIyaki.service.DataServiceInterface;
import terreIyaki.service.MyOrderServiceInterface;
import terreIyaki.service.MyUserServiceInterface;
import terreIyaki.tool.LongClass;

@RestController
@CrossOrigin("*")
public class TheMessageController implements Converter<String, Long>{

	private TheMessageRepository theMessageRepository;

	@Autowired
	private MyUserServiceInterface myUserServiceInterface;

	@Autowired
	private MyOrderServiceInterface myOrderServiceInterface;
	
	@Autowired
	private DataServiceInterface dataServiceInterface;
	

	public TheMessageController(TheMessageRepository theMessageRepository) {
		super();
		this.theMessageRepository = theMessageRepository;
	}

	// méthode qui va appeler la méthode qui va créer l'utilisateurt si condition
	// réuni
	@RequestMapping(value = "/getMessageCreateUser", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public TheMessage getMessageCreateUser(String email, String login, String lastName, String firstName) {
		MyUser myUser01 = new MyUser(email, login, lastName, firstName);
		return myUserServiceInterface.createMyUserMessage(myUser01);
	}

	// méthode qui va appeler la méthode qui va modifier l'utilisateurt si condition
	// réuni
	@RequestMapping(value = "/getMessageEditUser", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public TheMessage getMessageEditUser(String email, String login, String lastName, String firstName) {
		// System.out.println(" lastName : " +lastName);
		MyUser myUser01 = new MyUser(email, login, lastName, firstName);
		return myUserServiceInterface.editMyUserMessage(myUser01);
	}

	// méthode qui va créer un order item et le lier a myOrder
	// createOrderItem
	@RequestMapping(value = "/createOrderItem", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public TheMessage createOrderItem(Long productId, Long userId) {
		return myOrderServiceInterface.createOrderItem(productId, userId);
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
	private Long convert(LongClass EnterLongClass) {
		try {
			Long returnLong = Long.valueOf(String.valueOf(EnterLongClass.getIdLong()));
			return returnLong;
		} catch (NumberFormatException e) {
			return null;
		}
	}


	@RequestMapping(value = "/confirmOrder", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public TheMessage confirmOrder(Long userId) {
		
		//besoin condition si sur place ==> table !=null
		boolean test = myOrderServiceInterface.confirmOrderTestTable(userId);
		if (test) {
			return myOrderServiceInterface.confirmOrder(userId);
		}
		
		
		//(20,"Vous devez choisir choisir une table avant de valider");
		return theMessageRepository.findByNumber(20);

	}

	@RequestMapping(value = "/deleteOrder", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public TheMessage deleteOrder(Long userId) {
		return myOrderServiceInterface.deleteOrder(userId);
	}
	
	//on ouvre une commande a emporter ou sur place
	@RequestMapping(value = "/selectOrder", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public TheMessage selectOrder(String name, String email) {
		return myOrderServiceInterface.createMyOrderMessage(name, email);		
	}	

	//on ajoute la table a la commande sur place
	@RequestMapping(value = "/chooseTable", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public TheMessage chooseTable(int tableNumber, Long  userId) {
		return myOrderServiceInterface.chooseTable(tableNumber, userId);
	}
	
	//jeux d essai uniquement via l'URL
	@RequestMapping(value = "/jeuEssai01", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public TheMessage jeuEssai01() {
		return dataServiceInterface.jeuEssai01();
	}
	
	@RequestMapping(value = "/jeuEssai02", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public TheMessage jeuEssai02() {
		return dataServiceInterface.jeuEssai02();
	}

}