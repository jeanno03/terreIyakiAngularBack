package terreIyaki.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import terreIyaki.entity.Combo;
import terreIyaki.entity.MyOrder;
import terreIyaki.entity.MyTable;
import terreIyaki.entity.MyUser;
import terreIyaki.entity.OrderItem;
import terreIyaki.entity.OrderType;
import terreIyaki.entity.Product;
import terreIyaki.entity.Statut;
import terreIyaki.entity.TheMessage;
import terreIyaki.repository.ComboRepository;
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
	private MyTableRepository mytableRepository;
	
	@Autowired
	private ComboRepository comboRepository;
	

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
	public TheMessage chooseTable(Long tableId, Long  userId) {
		
		//on récupère la derniere commande de l'user
		MyOrder mo01 = myOrderRepository.selectLastMyOrderByUser(userId);
		
		//on récupère la table
		MyTable mt01 =  mytableRepository.findById(tableId);
		
		//je rajoute le numéro de la table
		mo01.setMyTable(mt01);
		
		//on modifie le statut de la table
		Statut st01 = statutRepository.findByNumero(2);
		mt01.setStatut(st01);
		
		//je persiste les changements
		mytableRepository.save(mt01);
		myOrderRepository.save(mo01);
		
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

		// je cherche le statut numéro 7
		Statut s01 = statutRepository.findByNumero(7);

		// je cherche la derniere commande
		MyOrder m01 = myOrderRepository.selectLastMyOrderByUser(userId);

		String comment = "produit ajouté";
		int quantite = 1;

		// on va chercher toutes les orderItems de la commande
		List<OrderItem> li01 = orderItemRepository.getOrderItemsByIdMyOrder(m01.getId());

		if (li01.isEmpty()) {
			// aucune commande donc je créé l orderItem
			OrderItem o00 = new OrderItem(p01.getPrice(), p01.getTax(), quantite, comment);

			o00.setProduct(p01);
			o00.setStatut(s01);
			o00.setMyOrder(m01);

			orderItemRepository.save(o00);

		} else {
			
			int chercher = 0;

			for (OrderItem o01 : li01) {

				// si le produit existe dans la commande j'incrémente de 1
				//soit chercher =1
				if (o01.getProduct().getId()==(productId)) {
					o01.setQuantite(o01.getQuantite() + 1);
					orderItemRepository.save(o01);
					chercher = 1;

			}
		}
			//si aucune incrémentation (chercher = 0) je créé l order Item
			if(chercher == 0) {
				 OrderItem o02 = new OrderItem(p01.getPrice(), p01.getTax(), quantite ,
				 comment);
				 o02.setProduct(p01);
				 o02.setStatut(s01);
				 o02.setMyOrder(m01);
				
				 orderItemRepository.save(o02);
			}
		}


		return theMessageRepository.findByNumber(7);

	}
	
	public TheMessage incrementeOrderItem(Long productId, Long userId) {
		
		// je cherche la derniere commande
		MyOrder m01 = myOrderRepository.selectLastMyOrderByUser(userId);
		
		// on va chercher toutes les orderItems de la commande
		List<OrderItem> li01 = orderItemRepository.getOrderItemsByIdMyOrder(m01.getId());
		
		// quand je retrouve le produit j'incremente de 1
		for (OrderItem o01 : li01) {
			
			if (o01.getProduct().getId()==(productId)) {
				o01.setQuantite(o01.getQuantite() + 1);
				orderItemRepository.save(o01);
		}
	}
		return theMessageRepository.findByNumber(9);
	}

	public TheMessage decrementeOrderItem(Long productId, Long userId) {
		
		// je cherche la derniere commande
		MyOrder m01 = myOrderRepository.selectLastMyOrderByUser(userId);
		
		// on va chercher toutes les orderItems de la commande
		List<OrderItem> li01 = orderItemRepository.getOrderItemsByIdMyOrder(m01.getId());
		
		// quand je retrouve le produit j'incremente de 1
		for (OrderItem o01 : li01) {
			
			if (o01.getProduct().getId()==(productId)) {
				o01.setQuantite(o01.getQuantite() - 1);
				orderItemRepository.save(o01);
		}
	}
		return theMessageRepository.findByNumber(10);
	}	
	
	
	
	
	public TheMessage deleteOrderItem(Long productId, Long userId) {
		
		// je cherche la derniere commande
		MyOrder m01 = myOrderRepository.selectLastMyOrderByUser(userId);
		
		// on va chercher toutes les orderItems de la commande
		List<OrderItem> li01 = orderItemRepository.getOrderItemsByIdMyOrder(m01.getId());
		
		// quand je retrouve le produit je le supprime
		for (OrderItem o01 : li01) {
			
			if (o01.getProduct().getId()==(productId)) {
				orderItemRepository.delete(o01);
		}
	}
		return theMessageRepository.findByNumber(11);
	}
	
	//méthode qui va créer l order item du combo, mess ==> 12
	public TheMessage createComboOrderItems(Long userId, Long comboId, List <Long> productsId) {
		
		// je cherche la derniere commande
		MyOrder m01 = myOrderRepository.selectLastMyOrderByUser(userId);
		
		//je cherche le combo
		Combo co01 = comboRepository.findById(comboId);
		
		//je recherche la liste de produit que j'ajoute à l'arrayList de produit
		List<Product> li01 = new ArrayList();
		for(Long l01 : productsId) {
			li01.add(productRepository.findById(l01));
		}

		//je créé toutes les orderItem
		//attention je ne rajoute que le prix du combo
		//les prix des produits ne doivent pas être ajoutés
		//pour le test j'ajoute le prix ttc une taxe a 1, quantité à 1
		OrderItem o00 = new OrderItem(co01.getVatPrice(), 0f, 1, "menu ajouté");
		
		o00.setCombo(co01);
		o00.setMyOrder(m01);
		orderItemRepository.save(o00);
		
for(int i=0;i<productsId.size();i++) {
Product po01 = productRepository.findById(productsId.get(i));
	OrderItem oi01 = new OrderItem(0f, 0f, 1, "produit du menu ajouté");
	oi01.setProduct(po01);
	oi01.setMyOrder(m01);
	orderItemRepository.save(oi01);
}
	
		return theMessageRepository.findByNumber(12);
	}
	
	
}
