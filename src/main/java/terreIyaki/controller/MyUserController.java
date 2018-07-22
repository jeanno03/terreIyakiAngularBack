package terreIyaki.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import terreIyaki.entity.MyUser;
import terreIyaki.repository.MyUserRepository;

@RestController
public class MyUserController {
	
	private MyUserRepository myUserRepository;

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

}
