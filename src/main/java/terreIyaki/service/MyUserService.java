package terreIyaki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import terreIyaki.entity.MyUser;
import terreIyaki.entity.TheMessage;
import terreIyaki.repository.MyUserRepository;
import terreIyaki.repository.TheMessageRepository;

@Service
public class MyUserService implements MyUserServiceInterface{
	
	@Autowired
	private MyUserRepository myUserRepository;
	
	@Autowired
	private TheMessageRepository theMessageRepository;
	
	
	//évolution de la création de l utilisateur
	//méthode non utilisé
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
				//ne fonctionne pas
			//	TheMessage theMessage01 = theMessageRepository.findByNumber("2");
				
			}
			
		}
		else {
			System.out.println("email non unique ");
		}
	
	return null;

	}
	
	
	//Méthode qui va controler l'unicité du login dans la BDD
	//si login unique création de l'utilisateur + message de succès par json
	//sinon renvoi un message d'erreur par json
	public TheMessage createMyUserMessage(MyUser myUser01) {
		
		MyUser myUser02= myUserRepository.getUserByEmail(myUser01.getEmail());
		MyUser myUser03= myUserRepository.getUserByLogin(myUser01.getLogin());
		
		//ce premier control est un sur control car une premiere vérification 
		// est réalisé en angular sur unicité de l email
		if (myUser02==null) {
			System.out.println("email unique ");

			if(myUser03==null) {
				System.out.println("login unique ");
				//l'utilisateur est créé
				//renvoi message de succès
				 myUserRepository.save(myUser01);
				return theMessageRepository.findByNumber(1);
			}
			else {
				System.out.println("login non unique ");
				//l utilisateur n'est pas créé 
				//renvoi du message d'erreur
				return theMessageRepository.findByNumber(2);
				
			}
			
		}
		else {
			System.out.println("email non unique ");
			//hypothétique message a créer est a renvoyer si le cas se présente
		}
	
	
		return null;
	}
	
	

}
