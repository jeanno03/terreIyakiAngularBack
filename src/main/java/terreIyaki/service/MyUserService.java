package terreIyaki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import terreIyaki.entity.MyUser;
import terreIyaki.repository.MyUserRepository;

@Service
public class MyUserService implements MyUserServiceInterface{
	
	@Autowired
	private MyUserRepository myUserRepository;
	
	public MyUser createMyUser(MyUser myUser01) {
		
		MyUser myUser02= myUserRepository.getUserByEmail(myUser01.getEmail());
		MyUser myUser03= myUserRepository.getUserByLogin(myUser01.getLogin());
		
		//ce premier control est un sur control car une premiere vérification 
		// est réalisé en angular sur unicité de l email
		if (myUser02==null) {
			System.out.println("email unique ");

			if(myUser03==null) {
				System.out.println("login unique ");
				return myUserRepository.save(myUser01);
			}
			else {
				System.out.println("login non unique ");
			}
			
		}
		else {
			System.out.println("email non unique ");
		}
	
	return null;

	}
	
	

}
