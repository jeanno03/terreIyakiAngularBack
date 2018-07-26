package terreIyaki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import terreIyaki.entity.MyUser;
import terreIyaki.entity.TheMessage;
import terreIyaki.repository.MyUserRepository;
import terreIyaki.repository.TheMessageRepository;

@Service
public class MyUserService implements MyUserServiceInterface {

	@Autowired
	private MyUserRepository myUserRepository;

	@Autowired
	private TheMessageRepository theMessageRepository;

	// évolution de la création de l utilisateur
	// méthode non utilisé
	public MyUser createMyUser(MyUser myUser01) {

		MyUser myUser02 = myUserRepository.getUserByEmail(myUser01.getEmail());
		MyUser myUser03 = myUserRepository.getUserByLogin(myUser01.getLogin());

		// ce premier control est un sur control car une premiere vérification
		// est réalisé en angular sur unicité de l email
		if (myUser02 == null) {
			System.out.println("email unique ");

			if (myUser03 == null) {
				System.out.println("login unique ");
				return myUserRepository.save(myUser01);
			} else {
				System.out.println("login non unique ");
				// ne fonctionne pas
				// TheMessage theMessage01 = theMessageRepository.findByNumber("2");

			}

		} else {
			System.out.println("email non unique ");
		}

		return null;

	}

	// Méthode qui va controler l'unicité du login dans la BDD
	// si login unique création de l'utilisateur + message de succès par json
	// sinon renvoi un message d'erreur par json
	public TheMessage createMyUserMessage(MyUser myUser01) {

		MyUser myUser02 = myUserRepository.getUserByEmail(myUser01.getEmail());
		MyUser myUser03 = myUserRepository.getUserByLogin(myUser01.getLogin());

		// ce premier control est un sur control car une premiere vérification
		// est réalisé en angular sur unicité de l email
		if (myUser02 == null) {
			System.out.println("email unique ");

			if (myUser03 == null) {
				System.out.println("login unique ");
				// l'utilisateur est créé
				// renvoi message de succès
				myUserRepository.save(myUser01);
				return theMessageRepository.findByNumber(1);
			} else {
				System.out.println("login non unique ");
				// l utilisateur n'est pas créé
				// renvoi du message d'erreur
				return theMessageRepository.findByNumber(2);

			}

		} else {
			System.out.println("email non unique ");
			// hypothétique message a créer est a renvoyer si le cas se présente
		}

		return null;
	}

	public TheMessage editMyUserMessage(MyUser myUser01) {

		// myUser01 correspond aux nouvelles données

		// myUser02 est l'utilisateur enregistré dans l'application
		MyUser myUser02 = myUserRepository.getUserByEmail(myUser01.getEmail());

		System.out.println("myUser01.getFirstName() : " + myUser01.getFirstName());

		System.out.println("myUser02.getTheId() : " + myUser02.getTheId());

		// au moin un des champs doit être différent pour prise
		// en compte de la modification de l'utilisateur
		// soit myUser01 != myUser02 sur au moin 1 champs

		if ((!myUser01.getLogin().equals(myUser02.getLogin()))
				|| (!myUser01.getFirstName().equals(myUser02.getFirstName()))
				|| (!myUser01.getLastName().equals(myUser02.getLastName()))) {
			// si condition réuni je modifie myUser02
			myUser02.setLogin(myUser01.getLogin());
			myUser02.setFirstName(myUser01.getFirstName());
			myUser02.setLastName(myUser01.getLastName());
			
			//il faut aussi que le login n existe pas dans la base
			MyUser myUser03 = myUserRepository.getUserByLogin(myUser01.getLogin());
			if (myUser03==null) {
				// puis je sauvegarde myUser02
				myUserRepository.save(myUser02);

				// je renvoie le message de succès
				return theMessageRepository.findByNumber(3);
				
			}

			//si le login existe dans la base je renvoie un message d echec
			else if (myUser03!=null) {
				return theMessageRepository.findByNumber(2);
			}

		}
		// si tous les champs sont identique
		// pas de sauvegarde + message d'erreur
		if (myUser01.getLogin().equals(myUser02.getLogin()) 
				&& myUser01.getFirstName().equals(myUser02.getFirstName())
				&& myUser01.getLastName().equals(myUser02.getLastName())) {
			return theMessageRepository.findByNumber(4);
		}

		return null;
	}

}
