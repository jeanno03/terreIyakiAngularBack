package terreIyaki.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import terreIyaki.entity.Category;
import terreIyaki.entity.CategoryMessage;
import terreIyaki.entity.Combo;
import terreIyaki.entity.ComboCategory;
import terreIyaki.entity.Comment;
import terreIyaki.entity.MyGrant;
import terreIyaki.entity.MyTable;
import terreIyaki.entity.MyUser;
import terreIyaki.entity.OrderType;
import terreIyaki.entity.Product;
import terreIyaki.entity.Statut;
import terreIyaki.entity.TheMessage;
import terreIyaki.entity.Vat;
import terreIyaki.repository.CategoryMessageRepository;
import terreIyaki.repository.CategoryRepository;
import terreIyaki.repository.ComboCategoryRepository;
import terreIyaki.repository.ComboRepository;
import terreIyaki.repository.CommentRepository;
import terreIyaki.repository.MyGrantRepository;
import terreIyaki.repository.MyTableRepository;
import terreIyaki.repository.MyUserRepository;
import terreIyaki.repository.OrderTypeRepository;
import terreIyaki.repository.ProductRepository;
import terreIyaki.repository.StatutRepository;
import terreIyaki.repository.TheMessageRepository;
import terreIyaki.repository.VatRepository;
import terreIyaki.tool.ComentTool;

@Service
public class MyUserService implements MyUserServiceInterface {

	//	@Autowired
	//	private StatutRepository statutRepository;

	@Autowired
	private MyUserRepository myUserRepository;

	@Autowired
	private TheMessageRepository theMessageRepository;

	@Autowired
	private CommentRepository commentRepository;


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

		// nouvelles données de l'utilisateur myUser01

		// myUser02 ==> données de l'utilisateur dans la base
		MyUser myUser02 = myUserRepository.getUserByEmail(myUser01.getEmail());	

		//Au moin un des champs de myUser01 et de myUser02 doit être différents
		if((!myUser01.getLogin().equals(myUser02.getLogin()))||
				(!myUser01.getFirstName().equals(myUser02.getFirstName()))||
				(!myUser01.getLastName().equals(myUser02.getLastName()))
				) {

			//si nv login = ancien ==> sauvegarde + message de succès
			if(myUser01.getLogin().equals(myUser02.getLogin())) {
				// l'utilisateur dans la base est modifier avec les nouvelles données puis enregistré
				myUser02.setFirstName(myUser01.getFirstName());
				myUser02.setLastName(myUser01.getLastName());
				myUserRepository.save(myUser02);
				return theMessageRepository.findByNumber(3);	

			}
			//si nv login différent de l'ancien
			else if(!myUser01.getLogin().equals(myUser02.getLogin())) {

				//si ce nv login n'existe pas dans la base
				//myUser03 est l'utilisateur energistré dans la base qui contient le meme login que le nv login choisi
				MyUser myUser03 = myUserRepository.getUserByLogin(myUser01.getLogin());	
				//si myUser03 est null alors le nv login n'existe pas ds la base
				//sauvegarde + message de succès
				try {
					if(myUser03.getLogin()==null) {
						// l'utilisateur dans la base est modifier avec les nouvelles données puis enregistré
						//si nv login existe deja dans la base	
						//message d'erreur
						//code dans le try and catch NullPointerException
					}
					else if(myUser03.getLogin()!=null) {
						return theMessageRepository.findByNumber(2);
					}
				}catch(NullPointerException ex) {
					System.out.println(ex);
					// l'utilisateur dans la base est modifier avec les nouvelles données puis enregistré
					myUser02.setLogin(myUser01.getLogin());
					myUser02.setFirstName(myUser01.getFirstName());
					myUser02.setLastName(myUser01.getLastName());
					myUserRepository.save(myUser02);
					return theMessageRepository.findByNumber(3);	
				}

			}

		}
		//si tous les champs sont identique ==> pas de sauvegarde + message d'erreur
		else if((myUser01.getLogin().equals(myUser02.getLogin()))&&
				(myUser01.getFirstName().equals(myUser02.getFirstName()))&&
				(myUser01.getLastName().equals(myUser02.getLastName()))
				) {
			return theMessageRepository.findByNumber(4);
		}			

		return null;
	}


	public TheMessage insertComment(String userId,ComentTool comment) {

		String co00 = comment.getComment();

		System.out.print(" co00 : "+ co00);

		Long userIdLong = Long.valueOf(userId);

		Date aujourdhui = new Date();

		MyUser my01 = myUserRepository.findById(userIdLong);

		Comment co01 = new Comment (aujourdhui, co00);
		co01.setMyUser(my01);

		commentRepository.save(co01);

		return theMessageRepository.findByNumber(21);
	}


}
