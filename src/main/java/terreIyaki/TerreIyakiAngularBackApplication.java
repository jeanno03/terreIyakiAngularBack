package terreIyaki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import terreIyaki.entity.*;
import terreIyaki.repository.*;

@SpringBootApplication
public class TerreIyakiAngularBackApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository CategoryRepository;

	@Autowired
	private VatRepository vatRepository;

	@Autowired
	private ComboRepository comboRepository;

	@Autowired
	private ComboCategoryRepository comboCategoryRepository;
	
	@Autowired
	private MyTableRepository myTableRepository;
	
	@Autowired
	private StatutRepository statutRepository;
	
	@Autowired
	private MyUserRepository myUserRepository;
	
	@Autowired
	private MyGrantRepository myGrantRepository;
	
	@Autowired
	private CategoryMessageRepository categoryMessageRepository;

	@Autowired
	private TheMessageRepository theMessageRepository;
	
	@Autowired
	private OrderTypeRepository orderTypeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TerreIyakiAngularBackApplication.class, args);
	}

	@Transactional
	public void run(String... arg0) throws Exception {
		Category category00 = new Category("Entrées");
		Category category01 = new Category("Plats");
		Category category02 = new Category("Desserts");
		Category category03 = new Category("Boissons");
		Category category04 = new Category("Menus");
		Vat vat01 = new Vat("TVA réduite", 0.055f);
		Vat vat02 = new Vat("TVA intermédiaire", 0.1f);
		Vat vat03 = new Vat("TVA normale", 0.2f);

		Product produit01 = new Product("Sushi saumon x2", 4.09f, "assets/images/sushiSaumonx2.jpeg",
				"Plat japonais composé d'un riz vinaigré appelé shari combiné avec du saumon cru de l'Atlantique",
				category01);
		Product produit02 = new Product("Porc Teriyaki", 12.27f, "assets/images/porcTerreIyaki.jpg",
				"Le Teriyaki est une sauce asiatique que l'on trouve toute prête dans les magasins asiatiques au rayon sauce. Elle est composée principalement de sauce soja, gingembre, sucre, eau",
				category01);
		Product produit03 = new Product("Coca-Cola 33 cl", 1.90f, "assets/images/Coca_Canette_33cl.png",
				"Célèbre boisson mythique", category03);
		Product produit04 = new Product("Perrier 33 cl", 1.90f, "assets/images/perrier-33cl.png",
				"Recommandé pour la digestion", category03);
		Product produit05 = new Product("Nouilles sauté au poulet à sauce japonaise", 8.18f,
				"assets/images/bento-poulet-frit.jpg",
				"Les Japonais préparent cette recette avec du bouillon de poulet, de la sauce soja, et surtout du mirin qui va apporter la saveur sucrée qu’on lui connaît bien",
				category01);

		Product produit06 = new Product("Salade saumon", 5.27f, "assets/images/salade_saumon_fume.jpg",
				"Salade verte avec pâtes orzo et edamame, mélange de laitues vertes, sauce à aneth, saumon fumé et crudités",
				category00);
		Product produit07 = new Product("Salade d'algues", 5f, "assets/images/salade_d_algues.JPG",
				"En plus d'être délicieuses, les algues sont très bonnes pour la santé", category00);

		Product produit08 = new Product("Perles de coco x2", 2.72f, "assets/images/perle-coco.jpeg",
				"La perle de coco est un dessert chinois à base de farine de riz gluant, fourré à la crème de haricot mungo et au sucre et saupoudré de copeaux de chair de noix de coco",
				category02);
		Product produit09 = new Product("Nougat lychee", 2.72f, "assets/images/nougat-lychee.jpeg", "Nougat mou sésame",
				category02);
			
		Category category101 = new Category("1 - Entrées");
		Category category102 = new Category("2 - Plats au choix");
		Category category103 = new Category("3 - Desserts au choix");
		
		Combo combo01 = new Combo("Menu brochettes MB3", 8.19f, "assets/images/mb3.jpg",
				"assortiment de 5 brochettes ou 4 brochettes de roulés de boeuf au fromage, accompagnés d'une soupe Miso et d'une salade de chou");
		
		ComboCategory comboCategory01 = new ComboCategory(1);
		ComboCategory comboCategory02 = new ComboCategory(2);
		
		comboCategory01.setCombo(combo01);
		comboCategory02.setCombo(combo01);
		
		comboCategory01.setCategory(category101);
		comboCategory02.setCategory(category102);

		Set produts01 = new HashSet<Product>() {
			{
				add(new Product("Soupe Miso et salade de chou japonaise", 2.72f, "assets/images/miso-et-salade.jpg",
						"Le miso, pâte de haricots de soja fermentée et salée, et sa salade de chou japonaise",
						category04, vat02));
			}
		};

		Set produts02 = new HashSet<Product>() {
			{
				add(new Product("Assortiment de 5 brochettes", 5.45f, "assets/images/5-brochettes.jpeg",
						"1 au boeuf, 1 au poulet, 1 au fromage, 1 aux boulettes de poulet, 1 aux ailes de poulet",
						category04, vat02));
				add(new Product("4 brochettes de roulés de boeuf au fromage", 5.45f, "assets/images/brochettes-boeuf-fromage.jpg",
						"Brochettes de boeuf au fromage - Yakitori",
						category04, vat02));
			}
		};
		
		comboCategory01.setProducts(produts01);
		comboCategory02.setProducts(produts02);
		
		Combo combo02 = new Combo("Menu M10", 15.45f, "assets/images/mb10.png",
				"8 sushi saumon ou thon accompagnés d'une soupe Miso et d'une salade de chou... avec un excellent dessert");
		
		Set produts04 = new HashSet<Product>() {
			{
				add(new Product("8 sushi saumon", 10.91f, "assets/images/8-sushi-saumon.jpeg",
						"Plat japonais composé d'un riz vinaigré appelé shari combiné avec du saumon cru de l'Atlantique",
						category04, vat02));
				add(new Product("8 sushi thon", 10.91f, "assets/images/8-sushi-thon.jpg",
						"Plat japonais composé d'un riz vinaigré appelé shari combiné avec du thon cru de l'Atlantique",
						category04, vat02));
			}
		};
		
		Set produts05 = new HashSet<Product>() {
			{
				add(new Product("Perles de coco x2", 1.81f, "assets/images/perle-coco.jpeg",
						"La perle de coco est un dessert chinois à base de farine de riz gluant, fourré à la crème de haricot mungo et au sucre et saupoudré de copeaux de chair de noix de coco",
						category04, vat02));
				add(new Product("Nougat lychee", 1.81f, "assets/images/nougat-lychee.jpeg",
						"Nougat mou sésame",
						category04, vat02));
			}
		};
		
		ComboCategory comboCategory03 = new ComboCategory(1);
		ComboCategory comboCategory04 = new ComboCategory(2);
		ComboCategory comboCategory05 = new ComboCategory(3);
		
		
		comboCategory03.setCombo(combo02);
		comboCategory04.setCombo(combo02);
		comboCategory05.setCombo(combo02);

		comboCategory03.setCategory(category101);
		comboCategory04.setCategory(category102);
		comboCategory05.setCategory(category103);
		
		comboCategory03.setProducts(produts01);
		comboCategory04.setProducts(produts04);
		comboCategory05.setProducts(produts05);
		
		produit01.setVat(vat02);
		produit02.setVat(vat02);
		produit03.setVat(vat01);
		produit04.setVat(vat01);
		produit05.setVat(vat02);

		produit06.setVat(vat02);
		produit07.setVat(vat02);

		produit08.setVat(vat02);
		produit09.setVat(vat02);
		
		MyTable table01 = new MyTable(1);
		MyTable table02 = new MyTable(2);
		MyTable table03 = new MyTable(3);
		MyTable table04 = new MyTable(4);
		MyTable table05 = new MyTable(5);
		MyTable table06 = new MyTable(6);
		MyTable table07 = new MyTable(7);
		MyTable table08 = new MyTable(8);
		MyTable table09 = new MyTable(9);
		MyTable table10 = new MyTable(10);
		
		Statut statut01 = new Statut(1, "libre");
		Statut statut02 = new Statut(2, "réservé");
		
		Statut statut03 = new Statut(3, "commande en cours");
		Statut statut04 = new Statut(4, "commande validée");
		Statut statut05 = new Statut(5, "commande réglée");
		Statut statut06 = new Statut(6, "commande annulée");
		
		Statut statut07 = new Statut(7, "produit en cours de commande");
		Statut statut08 = new Statut(8, "produit commandé");
		Statut statut09 = new Statut(9, "produit en préparation");
		Statut statut10 = new Statut(10, "produit préparation ok");
		Statut statut11 = new Statut(11, "produit servi");
		
		table01.setStatut(statut01);
		table02.setStatut(statut01);
		table03.setStatut(statut01);
		table04.setStatut(statut01);
		table05.setStatut(statut01);
		table06.setStatut(statut01);
		table07.setStatut(statut01);
		table08.setStatut(statut01);
		table09.setStatut(statut02);
		table10.setStatut(statut02);
		
		
		MyUser myUser01 = new MyUser("jeannory.phou@gmail.com","theBoss", "jeannory", "phou");
		MyUser myUser02 = new MyUser("terreiyaki@gmail.com","theKing", "terre", "iyaki");
		
		MyGrant myGrant01 = new MyGrant ("client");
		MyGrant myGrant02 = new MyGrant ("serveur");
		MyGrant myGrant03 = new MyGrant ("cuisinier");
		MyGrant myGrant04 = new MyGrant ("caissier");
		MyGrant myGrant05 = new MyGrant ("administrateur");
		
		Set myGrants01 = new HashSet<MyGrant>() {{
			add(myGrant02);
			add(myGrant03); 
			add(myGrant04);
			add(myGrant05);
		}};
		
		Set myGrants02 = new HashSet<MyGrant>() {{
			add(myGrant02);
			add(myGrant03); 
			add(myGrant04);
		}};
		
		myUser01.setMyGrants(myGrants01);
		myUser02.setMyGrants(myGrants02);
		
		CategoryMessage categoryMessage01 = new CategoryMessage (1, "succès");
		CategoryMessage categoryMessage02 = new CategoryMessage (2, "erreur");
		
		TheMessage theMessage01 = new TheMessage(1,"Félicitation votre compte a été créé");
		TheMessage theMessage02 = new TheMessage(2,"Erreur : login déjà utilisé, veuillez en choisir un autre");
		
		TheMessage theMessage03 = new TheMessage(3,"Votre compte a été modifié conformément à votre demande");
		TheMessage theMessage04 = new TheMessage(4,"Erreur : au moin un des champs doit être différent");
		
		TheMessage theMessage05 = new TheMessage(5,"Vous avez choisi de commander à emporter!");
		TheMessage theMessage06 = new TheMessage(6,"Vous avez choisi de commander sur place, veuillez choisir une table");
		
		TheMessage theMessage07 = new TheMessage(7,"Produit ajouté au panier!");
		
		TheMessage theMessage08 = new TheMessage(8,"Table réservée, veullez commander");
		
		TheMessage theMessage09 = new TheMessage(9,"Produit ajouté");
		TheMessage theMessage10 = new TheMessage(10,"Produit retiré");
		TheMessage theMessage11 = new TheMessage(11,"Ligne de produit supprimé");
		
		theMessage01.setCategoryMessage(categoryMessage01);
		theMessage02.setCategoryMessage(categoryMessage02);
		
		theMessage03.setCategoryMessage(categoryMessage01);
		theMessage04.setCategoryMessage(categoryMessage02);
		
		theMessage05.setCategoryMessage(categoryMessage01);
		theMessage06.setCategoryMessage(categoryMessage01);
		
		theMessage07.setCategoryMessage(categoryMessage01);
		
		theMessage08.setCategoryMessage(categoryMessage01);
		
		theMessage09.setCategoryMessage(categoryMessage01);
		theMessage10.setCategoryMessage(categoryMessage01);
		theMessage11.setCategoryMessage(categoryMessage01);
		
		
		OrderType orderType01 = new OrderType ("Sur place");
		OrderType orderType02 = new OrderType ("A emporter");

		productRepository.save(produts01);
		productRepository.save(produts02);	
		productRepository.save(produts04);
		productRepository.save(produts05);

		CategoryRepository.save(category00);
		CategoryRepository.save(category01);
		CategoryRepository.save(category02);
		CategoryRepository.save(category03);
		CategoryRepository.save(category04);
		
		CategoryRepository.save(category101);
		CategoryRepository.save(category102);
		CategoryRepository.save(category103);

		productRepository.save(produit01);
		productRepository.save(produit02);
		productRepository.save(produit03);
		productRepository.save(produit04);
		productRepository.save(produit05);
		productRepository.save(produit06);
		productRepository.save(produit07);
		productRepository.save(produit08);
		productRepository.save(produit09);

		vatRepository.save(vat01);
		vatRepository.save(vat02);
		vatRepository.save(vat03);

		comboRepository.save(combo01);
		comboRepository.save(combo02);

		comboCategoryRepository.save(comboCategory01);
		comboCategoryRepository.save(comboCategory02);
		comboCategoryRepository.save(comboCategory03);
		comboCategoryRepository.save(comboCategory04);
		comboCategoryRepository.save(comboCategory05);
		
		myTableRepository.save(table01);
		myTableRepository.save(table02);
		myTableRepository.save(table03);
		myTableRepository.save(table04);
		myTableRepository.save(table05);
		myTableRepository.save(table06);
		myTableRepository.save(table07);
		myTableRepository.save(table08);
		myTableRepository.save(table09);
		myTableRepository.save(table10);
		
		statutRepository.save(statut01);
		statutRepository.save(statut02);
		statutRepository.save(statut03);
		statutRepository.save(statut04);
		statutRepository.save(statut05);
		statutRepository.save(statut06);

		statutRepository.save(statut07);
		statutRepository.save(statut08);
		statutRepository.save(statut09);
		statutRepository.save(statut10);
		statutRepository.save(statut11);
		
		myGrantRepository.save(myGrants01);
		myGrantRepository.save(myGrants02);

		myGrantRepository.save(myGrant01);
		myGrantRepository.save(myGrant02);
		myGrantRepository.save(myGrant03);
		myGrantRepository.save(myGrant04);
		myGrantRepository.save(myGrant05);
		
		myUserRepository.save(myUser01);
		myUserRepository.save(myUser02);
		
		categoryMessageRepository.save(categoryMessage01);
		categoryMessageRepository.save(categoryMessage02);
		
		theMessageRepository.save(theMessage01);
		theMessageRepository.save(theMessage02);		
		
		theMessageRepository.save(theMessage03);
		theMessageRepository.save(theMessage04);
		
		theMessageRepository.save(theMessage05);
		theMessageRepository.save(theMessage06);
		theMessageRepository.save(theMessage07);
		theMessageRepository.save(theMessage08);
		
		theMessageRepository.save(theMessage09);
		theMessageRepository.save(theMessage10);
		theMessageRepository.save(theMessage11);
		
		orderTypeRepository.save(orderType01);
		orderTypeRepository.save(orderType02);
		
		productRepository.findAll().forEach(System.out::println);
		CategoryRepository.findAll().forEach(System.out::println);
		vatRepository.findAll().forEach(System.out::println);
		
		MyTable table101 = new MyTable(1);
		MyTable table102 = new MyTable(2);
		MyTable table103 = new MyTable(3);
		MyTable table104 = new MyTable(4);
		MyTable table105 = new MyTable(5);
		MyTable table106 = new MyTable(6);
		MyTable table107 = new MyTable(7);
		MyTable table108 = new MyTable(8);
		MyTable table109 = new MyTable(9);
		MyTable table110 = new MyTable(10);
		
		
		List<MyTable> myTableListProvisoire = new ArrayList();
		myTableListProvisoire.add(table106);
		myTableListProvisoire.add(table110);
		myTableListProvisoire.add(table104);
		myTableListProvisoire.add(table108);
		myTableListProvisoire.add(table101);
		myTableListProvisoire.add(table102);
		myTableListProvisoire.add(table109);
		myTableListProvisoire.add(table107);
		myTableListProvisoire.add(table105);
		myTableListProvisoire.add(table103);
		
		
		int i=0;
		int changement;
		int provi=1;
		
		for(int j=0;j<myTableListProvisoire.size();j++) {
	    	System.out.println("Avant trie - Numéro : (" + myTableListProvisoire.get(j).getTableNumber()+")");
	    }
				
 	
		
	    
		 for( int j = 0 ; j < provi  ; j++) { 
			 
			 changement=0;
			 

		 
	        for (i = 0; i < myTableListProvisoire.size() - 1; i++) {	
	        	
	        	
	        	
	            if (myTableListProvisoire.get(i).getTableNumber() > myTableListProvisoire.get(i + 1).getTableNumber()) {

	            	MyTable myTableModelProvisoire = new MyTable (myTableListProvisoire.get(i).getTableNumber());

	              myTableListProvisoire.get(i).setTableNumber(myTableListProvisoire.get(i + 1).getTableNumber());
	              
	              myTableListProvisoire.get(i + 1).setTableNumber(myTableModelProvisoire.getTableNumber());
	              
	              changement=1;	             

	            }        
	            


	        }
	        
            if(changement!=0) {
            	provi=provi+1;
            }

	 }

		 System.out.println("\n*************************************");
		 
	    for(int j=0;j<myTableListProvisoire.size();j++) {
	    	System.out.println("Après trie - Numéro : (" + myTableListProvisoire.get(j).getTableNumber()+")");
	    }
		
		
		 System.out.println("\nnombre total de vérification : " + provi);
		 System.out.println("\n*************************************");
		 
	    System.out.println("\nvérification avec un tableau déjà trié");
	    System.out.println("\n*************************************");
	    
		MyTable table201 = new MyTable(1);
		MyTable table202 = new MyTable(2);
		MyTable table203 = new MyTable(3);
		MyTable table204 = new MyTable(4);
		MyTable table205 = new MyTable(5);
		MyTable table206 = new MyTable(6);
		MyTable table207 = new MyTable(7);
		MyTable table208 = new MyTable(8);
		MyTable table209 = new MyTable(9);
		MyTable table210 = new MyTable(10);
	    
	    
		
		
	    List<MyTable> myTableListProvisoire02 = new ArrayList();
	    myTableListProvisoire02.add(table201);
	    myTableListProvisoire02.add(table202);
	    myTableListProvisoire02.add(table203);
	    myTableListProvisoire02.add(table204);
	    myTableListProvisoire02.add(table205);
	    myTableListProvisoire02.add(table206);
	    myTableListProvisoire02.add(table207);
	    myTableListProvisoire02.add(table208);
	    myTableListProvisoire02.add(table209);
	    myTableListProvisoire02.add(table210);
		
	
		for(int j=0;j<myTableListProvisoire02.size();j++) {
	    	System.out.println("Avant trie - Numéro : (" + myTableListProvisoire02.get(j).getTableNumber()+")");
	    }
		
			i=0;
		int provi02=1;
		int changement02 ;
		
	    
		 for( int j = 0 ; j < provi02  ; j++) { 
			 
			 changement02=0;
			 
		 
	        for (i = 0; i < myTableListProvisoire02.size() - 1; i++) {	
	        		
	        	
	            if (myTableListProvisoire02.get(i).getTableNumber() > myTableListProvisoire02.get(i + 1).getTableNumber()) {

	            	MyTable myTableModelProvisoire = new MyTable (myTableListProvisoire02.get(i).getTableNumber());

	            	myTableListProvisoire02.get(i).setTableNumber(myTableListProvisoire02.get(i + 1).getTableNumber());
	              
	            	myTableListProvisoire02.get(i + 1).setTableNumber(myTableModelProvisoire.getTableNumber());
	              
	            	changement02=1;	             

	            }        
	            


	        }
	        
            if(changement02!=0) {
            	provi02=provi02+1;
            }

	 }
		 System.out.println("\n*************************************");
	    
	    for(int j=0;j<myTableListProvisoire02.size();j++) {
	    	System.out.println("Après trie - Numéro : (" + myTableListProvisoire02.get(j).getTableNumber()+")");
	    }
		
		
		
		 System.out.println("\nnombre total de vérification : " + provi02);
		
		
		
		
		
		
		

	}
}