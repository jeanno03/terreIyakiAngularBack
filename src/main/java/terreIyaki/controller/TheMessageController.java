package terreIyaki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import terreIyaki.entity.MyUser;
import terreIyaki.entity.TheMessage;
import terreIyaki.repository.TheMessageRepository;
import terreIyaki.service.MyOrderServiceInterface;
import terreIyaki.service.MyUserServiceInterface;

@RestController
@CrossOrigin("*")
public class TheMessageController {

	private TheMessageRepository theMessageRepository;

	@Autowired
	private MyUserServiceInterface myUserServiceInterface;

	@Autowired
	private MyOrderServiceInterface myOrderServiceInterface;

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

}