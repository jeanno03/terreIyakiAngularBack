package terreIyaki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import terreIyaki.entity.MyUser;
import terreIyaki.repository.MyUserRepository;
import terreIyaki.service.MyUserServiceInterface;

@RestController
public class MyUserController {
	
	private MyUserRepository myUserRepository;
	
	@Autowired
	private MyUserServiceInterface myUserServiceInterface;

	public MyUserController(MyUserRepository myUserRepository) {
		super();
		this.myUserRepository = myUserRepository;
	}


	@RequestMapping(value = "/getUserByEmail", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public MyUser getUserByEmail(String email) {
		MyUser myuser01 = myUserRepository.getUserByEmail(email);
		return myuser01;
		
	}
	
	@RequestMapping(value = "/getUserByLogin", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	MyUser getUserByLogin(String login) {
		MyUser myuser01 = myUserRepository.getUserByLogin(login);
		return myuser01;
	}
	
	//Evolution de l'application : ****** méthode non utilisé ********
	//va appeler une méthode qui va controler l'unicité de l'email et du login 
	//et sauvegarder MyUser
	@RequestMapping(value = "/tryAndSaveMyUser", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public MyUser findUserByEmail(String email, String login, String lastName, String firstName) {
		MyUser myUser01 = myUserRepository.getUserByEmail(email);
		MyUser myUser02= new MyUser(email, login, lastName, firstName);
		return myUserServiceInterface.createMyUser(myUser02);
		
	}
	
	

}
