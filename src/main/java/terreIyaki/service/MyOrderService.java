package terreIyaki.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;


import terreIyaki.entity.Combo;
import terreIyaki.entity.Historisation;
import terreIyaki.entity.MyOrder;
import terreIyaki.entity.MyTable;
import terreIyaki.entity.MyUser;
import terreIyaki.entity.OrderItem;
import terreIyaki.entity.OrderType;
import terreIyaki.entity.Product;
import terreIyaki.entity.Statut;
import terreIyaki.entity.TheMessage;
import terreIyaki.repository.ComboRepository;
import terreIyaki.repository.HistorisationRepository;
import terreIyaki.repository.MyOrderRepository;
import terreIyaki.repository.MyTableRepository;
import terreIyaki.repository.MyUserRepository;
import terreIyaki.repository.OrderItemRepository;
import terreIyaki.repository.OrderTypeRepository;
import terreIyaki.repository.ProductRepository;
import terreIyaki.repository.StatutRepository;
import terreIyaki.repository.TheMessageRepository;

@Service
public class MyOrderService implements MyOrderServiceInterface {

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

	@Autowired
	private MyTableRepository myTableRepository;

	@Autowired
	private ComboRepository comboRepository;

	@Autowired
	private HistorisationRepository historisationRepository;

	//méthode de création de la commande
	@Override
	public TheMessage createMyOrderMessage(String name, String email) {

		// orderType01 est le orderType choisi 
		OrderType orderType02 = orderTypeRepository.findByName(name);

		// myUser01 est l'utilisateur qui effectue l'action
		MyUser myUser01 = myUserRepository.getUserByEmail(email);

		// statut01 est le statut de la commande
		Statut statut01 = statutRepository.getStatutByName("commande en cours");

		Date aujourdhui = new Date();

		MyOrder myOrder01 = new MyOrder(aujourdhui);

		myOrder01.setOrderType(orderType02);
		myOrder01.setMyUser(myUser01);
		myOrder01.setStatut(statut01);

		myOrderRepository.save(myOrder01);

		// message de succès sur place ou a emporter
		if (name.equals("A emporter")) {
			return theMessageRepository.findByNumber(5);
		} else if (name.equals("Sur place")) {
			return theMessageRepository.findByNumber(6);
		}
		return null;

	}


	//Méthode qui va ajouter le numéro de table à la commande
	public TheMessage chooseTable(int tableNumber, Long  userId) {

		//on récupère la derniere commande de l'user
		//		MyOrder mo01 = myOrderRepository.selectLastMyOrderByUser(userId);

		//on veut la derniere commande
		MyOrder m01 = new MyOrder();
		try {
			//on a toute les commande de l utilisateur
			//			on cherche commande avec statut ==> 3, "commande en cours";
			List<MyOrder> li01= myOrderRepository.findByStatutNumeroAndMyUserIdOrderByIdDesc(3, userId);
			System.out.println("toutes les commandes de l'user : " +li01);

			//on récupère la 1ere valeure
			m01 = li01.get(0);
			System.out.println("last commande de l'user en cours : " +m01);


		}catch(NullPointerException ex) {
			System.out.println(ex);
		}catch(IndexOutOfBoundsException ex) {
			System.out.println(ex);
		}


		//on récupère la table
		//		MyTable mt01 =  myTableRepository.findById(tableId);

		//l'algo a été modifié car en angular j'avais besoin du numéro de table en parametre
		MyTable mt01 =  myTableRepository.findByTableNumber(tableNumber);


		//je rajoute le numéro de la table
		m01.setMyTable(mt01);

		//on modifie le statut de la table
		Statut st01 = statutRepository.findByNumero(2);
		mt01.setStatut(st01);

		//je persiste les changements
		myTableRepository.save(mt01);
		myOrderRepository.save(m01);

		//je renvoi le message de succès
		return theMessageRepository.findByNumber(8);

	}

	// méthode qui va créé orderItem, récupérer le last myOrder
	// elle va rechercher statut.numero=7
	// elle va faire un orderItem set ProductId, statutId=9
	// va retourner la list de tous les ordersItems de la commande
	@Override
	public TheMessage createOrderItem(Long productId, Long userId) {

		// je cherche le produit via son id
		Product p01 = productRepository.findById(productId);

		// je cherche le statut numéro 7 "produit en cours de commande"
		Statut s01 = statutRepository.findByNumero(7);

		// je cherche la derniere commande
		//		MyOrder m01 = myOrderRepository.selectLastMyOrderByUser(userId);

		//on veut la derniere commande
		MyOrder m01 = new MyOrder();
		try {
			//on a toute les commande de l utilisateur
			//			on cherche commande avec statut ==> 3, "commande en cours";
			List<MyOrder> li01= myOrderRepository.findByStatutNumeroAndMyUserIdOrderByIdDesc(3, userId);
			System.out.println("toutes les commandes de l'user : " +li01);

			//on récupère la 1ere valeure
			m01 = li01.get(0);
			System.out.println("last commande de l'user en cours : " +m01);


		}catch(NullPointerException ex) {
			System.out.println(ex);
		}catch(IndexOutOfBoundsException ex) {
			System.out.println(ex);
		}


		String comment = "produit ajouté";
		int quantite = 1;

		try {
			// on va chercher toutes les orderItems de la commande
			List<OrderItem> li01 = orderItemRepository.getOrderItemsByIdMyOrder(m01.getId());

			if (li01.isEmpty()) {
				// aucune commande donc je créé l orderItem
				OrderItem o00 = new OrderItem(p01.getPrice(), p01.getTax(), quantite, comment, 0f);

				o00.setProduct(p01);
				o00.setStatut(s01);
				o00.setMyOrder(m01);

				orderItemRepository.save(o00);

			} else {

				int chercher = 0;

				for (OrderItem o01 : li01) {


					try {
						// si le produit existe dans la commande j'incrémente de 1
						//soit chercher =1
						if (o01.getProduct().getId()==(productId)) {
							o01.setQuantite(o01.getQuantite() + 1);
							//					Le statut deja à jour	
							//					o01.setStatut(s01);
							orderItemRepository.save(o01);
							chercher = 1;

						}

					}catch(NullPointerException ex)	{
						//on passe au tour suivant s'il s'agit 'un combo (o01.getProduct().getId()=null)
					}

				}
				//si aucune incrémentation (chercher = 0) je créé l order Item
				if(chercher == 0) {
					OrderItem o02 = new OrderItem(p01.getPrice(), p01.getTax(), quantite ,
							comment, 0f);
					o02.setProduct(p01);
					o02.setStatut(s01);
					o02.setMyOrder(m01);

					orderItemRepository.save(o02);
				}
			}
		}catch(NullPointerException ex) {
			//si liste de produit vide
			System.out.println(ex);
		}

		return theMessageRepository.findByNumber(7);

	}

	public TheMessage incrementeOrderItem(Long productId, Long userId) {

		// je cherche la derniere commande
		//		MyOrder m01 = myOrderRepository.selectLastMyOrderByUser(userId);

		//on veut la derniere commande
		MyOrder m01 = new MyOrder();
		try {
			//on a toute les commande de l utilisateur
			//			on cherche commande avec statut ==> 3, "commande en cours";
			List<MyOrder> li01= myOrderRepository.findByStatutNumeroAndMyUserIdOrderByIdDesc(3, userId);
			System.out.println("toutes les commandes de l'user : " +li01);

			//on récupère la 1ere valeure
			m01 = li01.get(0);
			System.out.println("last commande de l'user en cours : " +m01);


		}catch(NullPointerException ex) {
			System.out.println(ex);
		}catch(IndexOutOfBoundsException ex) {
			System.out.println(ex);
		}


		// on va chercher toutes les orderItems de la commande
		List<OrderItem> li01 = orderItemRepository.getOrderItemsByIdMyOrder(m01.getId());

		// quand je retrouve le produit j'incremente de 1
		for (OrderItem o01 : li01) {
			try {
				// si le produit existe dans la commande j'incrémente de 1
				//soit chercher =1
				if (o01.getProduct().getId()==(productId)) {
					o01.setQuantite(o01.getQuantite() + 1);
					orderItemRepository.save(o01);
				}
			}catch(NullPointerException ex)	{
				//on passe au tour suivant s'il s'agit 'un combo (o01.getProduct().getId()=null)
			}
		}
		return theMessageRepository.findByNumber(9);
	}

	public TheMessage decrementeOrderItem(Long productId, Long userId) {

		// je cherche la derniere commande
		//		MyOrder m01 = myOrderRepository.selectLastMyOrderByUser(userId);

		//on veut la derniere commande
		MyOrder m01 = new MyOrder();
		try {
			//on a toute les commande de l utilisateur
			//			on cherche commande avec statut ==> 3, "commande en cours";
			List<MyOrder> li01= myOrderRepository.findByStatutNumeroAndMyUserIdOrderByIdDesc(3, userId);
			System.out.println("toutes les commandes de l'user : " +li01);

			//on récupère la 1ere valeure
			m01 = li01.get(0);
			System.out.println("last commande de l'user en cours : " +m01);


		}catch(NullPointerException ex) {
			System.out.println(ex);
		}catch(IndexOutOfBoundsException ex) {
			System.out.println(ex);
		}


		// on va chercher toutes les orderItems de la commande
		List<OrderItem> li01 = orderItemRepository.getOrderItemsByIdMyOrder(m01.getId());

		// quand je retrouve le produit j'incremente de 1
		for (OrderItem o01 : li01) {
			// si le produit existe dans la commande j'incrémente de 1
			//soit chercher =1
			try {
				if (o01.getProduct().getId()==(productId)) {
					o01.setQuantite(o01.getQuantite() - 1);
					orderItemRepository.save(o01);
				}
			}catch(NullPointerException ex)	{
				//on passe au tour suivant s'il s'agit 'un combo (o01.getProduct().getId()=null)
			}
		}
		return theMessageRepository.findByNumber(10);
	}	




	public TheMessage deleteOrderItem(Long productId, Long userId) {

		// je cherche la derniere commande
		//		MyOrder m01 = myOrderRepository.selectLastMyOrderByUser(userId);

		//on veut la derniere commande
		MyOrder m01 = new MyOrder();
		try {
			//on a toute les commande de l utilisateur
			//			on cherche commande avec statut ==> 3, "commande en cours";
			List<MyOrder> li01= myOrderRepository.findByStatutNumeroAndMyUserIdOrderByIdDesc(3, userId);
			System.out.println("toutes les commandes de l'user : " +li01);

			//on récupère la 1ere valeure
			m01 = li01.get(0);
			System.out.println("last commande de l'user en cours : " +m01);


		}catch(NullPointerException ex) {
			System.out.println(ex);
		}catch(IndexOutOfBoundsException ex) {
			System.out.println(ex);
		}


		// on va chercher toutes les orderItems de la commande
		List<OrderItem> li01 = orderItemRepository.getOrderItemsByIdMyOrder(m01.getId());

		// quand je retrouve le produit je le supprime
		for (OrderItem o01 : li01) {

			try {
				if (o01.getProduct().getId()==(productId)) {
					orderItemRepository.delete(o01);
				}
			}catch(NullPointerException ex)	{
				//on passe au tour suivant s'il s'agit 'un combo (o01.getProduct().getId()=null)
			}
		}
		return theMessageRepository.findByNumber(11);
	}



	//set a faire pour menu et pour product
	//méthode qui va créer l order item du combo, mess ==> 12
	public TheMessage createComboOrderItems(Long userId, Long comboId, List <Long> productsId) {
		//je cherche l'user
		MyUser my01 = myUserRepository.findById(userId);

		// je cherche la derniere commande
		//		MyOrder m01 = myOrderRepository.selectLastMyOrderByUser(userId);


		//on veut la derniere commande
		MyOrder m01 = new MyOrder();
		try {
			//on a toute les commande de l utilisateur
			//			on cherche commande avec statut ==> 3, "commande en cours";
			List<MyOrder> li01= myOrderRepository.findByStatutNumeroAndMyUserIdOrderByIdDesc(3, userId);
			System.out.println("toutes les commandes de l'user : " +li01);

			//on récupère la 1ere valeure
			m01 = li01.get(0);
			System.out.println("last commande de l'user en cours : " +m01);


		}catch(NullPointerException ex) {
			System.out.println(ex);
		}catch(IndexOutOfBoundsException ex) {
			System.out.println(ex);
		}



		//je cherche le combo
		Combo co01 = comboRepository.findById(comboId);

		// je cherche le statut numéro 7 "produit en cours de commande"
		Statut s01 = statutRepository.findByNumero(7);

		//12, "menu en cours de commande";
		Statut s02 = statutRepository.findByNumero(12);

		//je recherche la liste de produit que j'ajoute à l'arrayList de produit
		//		List<Product> li01 = new ArrayList();
		//		for(Long l01 : productsId) {
		//			li01.add(productRepository.findById(l01));
		//		}

		//cette list va contenir tous les ordersItems sauvegardé dans historisation
		//menu et produits
		//cela va me servir a retrouver les produits du menus
		Set orderItems01 = new HashSet<OrderItem>();

		//je créé toutes les orderItem
		//attention je ne rajoute que le prix du combo
		//les prix des produits ne doivent pas être ajoutés
		//pour le test j'ajoute le prix ttc une taxe a 1, quantité à 1
		OrderItem o00 = new OrderItem(0f, 0f, 1, "menu ajouté", co01.getVatPrice());

		o00.setCombo(co01);
		o00.setMyOrder(m01);
		o00.setStatut(s02);

		orderItems01.add(o00);

		orderItemRepository.save(o00);

		for(int i=0;i<productsId.size();i++) {
			Product po01 = productRepository.findById(productsId.get(i));
			OrderItem oi01 = new OrderItem(po01.getPrice(), po01.getTax(), 1, "produit du menu ajouté", 0f);
			oi01.setProduct(po01);
			oi01.setMyOrder(m01);
			oi01.setStatut(s01);
			//je rajoute tous les orderItem à Set orderItems01
			orderItems01.add(oi01);
			orderItemRepository.save(oi01);
		}


		orderItemRepository.save(orderItems01);


		//jenregistre dans historisation le user/ le myOrder/ la list de orderItems
		//cest tel user qui a enregistré les orders items de cette commande...
		Date aujourdhui = new Date();
		Historisation hi01 = new Historisation(aujourdhui);



		hi01.setMyUser(my01);
		hi01.setMyOrder(m01);
		hi01.setOrderItems(orderItems01);

		historisationRepository.save(hi01);
		//J'envoi en parametre dans la méthode createComboOrderItems


		return theMessageRepository.findByNumber(12);
	}

	//	mess=>14 ==> supprimer tous les orderItems du menu
	public TheMessage deleteComboOrderItem(List <Long> orderItemIds) {

		// quand je retrouve la commande je le supprime de tous les orderItemIds

		for(Long lo01 : orderItemIds) {
			try {
				OrderItem oi01 = orderItemRepository.findById(lo01);
				orderItemRepository.delete(oi01);
			}catch(NullPointerException ex)	{
				System.out.println(ex);
			}
		}



		return theMessageRepository.findByNumber(14);
	}


	public boolean confirmOrderTestTable(Long userId) {

		//on veut la derniere commande
		MyOrder m01 = new MyOrder();
		try {
			//on a toute les commande de l utilisateur
			//			on cherche commande avec statut ==> 3, "commande en cours";
			List<MyOrder> li01= myOrderRepository.findByStatutNumeroAndMyUserIdOrderByIdDesc(3, userId);
			System.out.println("toutes les commandes de l'user : " +li01);

			//on récupère la 1ere valeure
			m01 = li01.get(0);
			System.out.println("last commande de l'user en cours : " +m01);


		}catch(NullPointerException ex) {
			System.out.println(ex);
		}catch(IndexOutOfBoundsException ex) {
			System.out.println(ex);
		}

		if(m01.getOrderType().getName().equals("Sur place")&&m01.getMyTable()==null) {
			return false;
		}
		return true;

	}



	//mess 15
	//on valide la commande
	public TheMessage confirmOrder(Long userId) {

		// je cherche la derniere commande
		//		MyOrder m01 = myOrderRepository.selectLastMyOrderByUser(userId);


		//on veut la derniere commande
		MyOrder m01 = new MyOrder();
		try {
			//on a toute les commande de l utilisateur
			//			on cherche commande avec statut ==> 3, "commande en cours";
			List<MyOrder> li01= myOrderRepository.findByStatutNumeroAndMyUserIdOrderByIdDesc(3, userId);
			System.out.println("toutes les commandes de l'user : " +li01);

			//on récupère la 1ere valeure
			m01 = li01.get(0);
			System.out.println("last commande de l'user en cours : " +m01);


		}catch(NullPointerException ex) {
			System.out.println(ex);
		}catch(IndexOutOfBoundsException ex) {
			System.out.println(ex);
		}


		// on va chercher toutes les orderItems de la commande
		List<OrderItem> li01 = orderItemRepository.getOrderItemsByIdMyOrder(m01.getId());

		//je modifie le statut de la commande
		//Statut statut04 = new Statut(4, "commande validée");
		Statut st01 = statutRepository.findByNumero(4);

		// je modifie tous les statut de orderItem
		//Statut statut09 = new Statut(9, "produit en préparation");
		Statut st02 = statutRepository.findByNumero(9);

		//Statut statut13 = new Statut(13, "menu commandé");
		Statut st03 = statutRepository.findByNumero(13);


		//on remet le statut de la table a libre
		try {
			//on va chercher la table
			MyTable my01=m01.getMyTable();
			//Statut statut01 = new Statut(1, "libre");
			Statut st04 = statutRepository.findByNumero(1);
			my01.setStatut(st04);
			myTableRepository.save(my01);

		}catch(NullPointerException ex) {
			System.out.println(ex);		
		}


		m01.setStatut(st01);
		myOrderRepository.save(m01);

		for (OrderItem o01 : li01) {

			try {

				if(o01.getIdProduct()!=null) {
					//si produit je met statut 9
					o01.setStatut(st02);
				}
				else if(o01.getComboName()!=null) {
					//si produit inexistant ==> c est un menu
					//je met satu 13
					o01.setStatut(st03);
				}

				orderItemRepository.save(o01);

			}catch(NullPointerException ex)	{
				System.out.println(ex);

			}
		}
		return theMessageRepository.findByNumber(15);
	}

	//Je supprime les orderItem et l'historisation
	//je modifie le statut de la commande
	//retour mess 17
	public TheMessage deleteOrder(Long userId) {
		// je cherche la derniere commande
		//		MyOrder m01 = myOrderRepository.selectLastMyOrderByUser(userId);

		//on veut la derniere commande
		MyOrder m01 = new MyOrder();
		try {
			//on a toute les commande de l utilisateur
			//			on cherche commande avec statut ==> 3, "commande en cours";
			List<MyOrder> li01= myOrderRepository.findByStatutNumeroAndMyUserIdOrderByIdDesc(3, userId);
			System.out.println("toutes les commandes de l'user : " +li01);

			//on récupère la 1ere valeure
			m01 = li01.get(0);
			System.out.println("last commande de l'user en cours : " +m01);


		}catch(NullPointerException ex) {
			System.out.println(ex);
		}catch(IndexOutOfBoundsException ex) {
			System.out.println(ex);
		}



		//		Statut statut14 = new Statut(14, "annulé");
		Statut st01 = statutRepository.findByNumero(14);

		//on remet le statut de la table a libre
		try {
			//on va chercher la table
			MyTable my01=m01.getMyTable();
			//Statut statut01 = new Statut(1, "libre");
			Statut st04 = statutRepository.findByNumero(1);
			my01.setStatut(st04);
			myTableRepository.save(my01);

		}catch(NullPointerException ex) {
			System.out.println(ex);		
		}


		//on va chercher l'historisation
		try {
			Set<Historisation> hi01 = historisationRepository.findByMyOrderId(m01.getId());
			//		historisationRepository.delete(hi01);
			//j'annule l'historisation

			for(Historisation hi : hi01) {
				hi.setStatut(st01);
				historisationRepository.save(hi);
			}

		}catch(NullPointerException ex) {
			System.out.println(ex);
		}

		//je supprime les orderItem, historisation, myOrder

		try {
			// on va chercher toutes les orderItems de la commande
			List<OrderItem> li01 = orderItemRepository.getOrderItemsByIdMyOrder(m01.getId());

			for(OrderItem or01:li01) {
				//				orderItemRepository.delete(or01);
				//orderItem==> inactif
				or01.setStatut(st01);
			}

		}catch (NullPointerException ex) {
			System.out.println(ex);
		}


		//suppression impossible a cause de la dépendance de la table
		//on change le statut de la commande

		//		Statut statut06 = new Statut(6, "commande annulée");
		Statut st05 = statutRepository.findByNumero(6);
		m01.setStatut(st05);
		myOrderRepository.save(m01);

		return theMessageRepository.findByNumber(17);

	}

	public MyOrder getLastOrderByUser(Long userId)throws SQLException{

		//on veut la derniere commande
		try {
			//on a toute les commande de l utilisateur
			//			on cherche commande avec statut ==> 3, "commande en cours";
			List<MyOrder> li01= myOrderRepository.findByStatutNumeroAndMyUserIdOrderByIdDesc(3, userId);
			System.out.println("toutes les commandes de l'user : " +li01);

			//on récupère la 1ere valeure
			MyOrder m01 = li01.get(0);
			System.out.println("last commande de l'user en cours : " +m01);
			return m01;

		}catch(NullPointerException ex) {
			System.out.println(ex);
		}catch(IndexOutOfBoundsException ex) {
			System.out.println(ex);
		}



		return null;
	}

}