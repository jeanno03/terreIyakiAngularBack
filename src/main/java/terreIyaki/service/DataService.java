package terreIyaki.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import terreIyaki.entity.Category;
import terreIyaki.entity.CategoryMessage;
import terreIyaki.entity.Combo;
import terreIyaki.entity.ComboCategory;
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
import terreIyaki.repository.MyGrantRepository;
import terreIyaki.repository.MyTableRepository;
import terreIyaki.repository.MyUserRepository;
import terreIyaki.repository.OrderTypeRepository;
import terreIyaki.repository.ProductRepository;
import terreIyaki.repository.StatutRepository;
import terreIyaki.repository.TheMessageRepository;
import terreIyaki.repository.VatRepository;



@Service
public class DataService implements DataServiceInterface{
	

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

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
	
	public TheMessage jeuEssai01() {
	
	//*********************début jeu d'essai 01 *********************************
	
			Category category000 = new Category("Entrées");
			Category category010 = new Category("Plats");
			Category category020 = new Category("Desserts");
			Category category030 = new Category("Boissons");
			Category category040 = new Category("Menus");
			
			
			Category category1010 = new Category("1 - Entrées");
			Category category1020 = new Category("2 - Plats au choix");
			Category category1030 = new Category("3 - Desserts au choix");
			
			
			categoryRepository.save(category000);
			categoryRepository.save(category010);
			categoryRepository.save(category020);
			categoryRepository.save(category030);
			categoryRepository.save(category040);

			categoryRepository.save(category1010);
			categoryRepository.save(category1020);
			categoryRepository.save(category1030);
			
			
			Category category00 = categoryRepository.findByNameIgnoreCase("Entrées");
			Category category01 = categoryRepository.findByNameIgnoreCase("Plats");
			Category category02 = categoryRepository.findByNameIgnoreCase("Desserts");
			Category category03 = categoryRepository.findByNameIgnoreCase("Boissons");
			Category category04 = categoryRepository.findByNameIgnoreCase("Menus");
			
			Category category101 = categoryRepository.findByNameIgnoreCase("1 - Entrées");
			Category category102 = categoryRepository.findByNameIgnoreCase("2 - Plats au choix");
			Category category103 = categoryRepository.findByNameIgnoreCase("3 - Desserts au choix");
			
			
			
			
			Vat vat010 = new Vat("TVA réduite", 0.055f);
			Vat vat020 = new Vat("TVA intermédiaire", 0.1f);
			Vat vat030 = new Vat("TVA normale", 0.2f);
			
			vatRepository.save(vat010);
			vatRepository.save(vat020);
			vatRepository.save(vat030);
			
			Vat vat01 = vatRepository.findByLabelIgnoreCase("TVA réduite");
			Vat vat02 = vatRepository.findByLabelIgnoreCase("TVA intermédiaire");
			Vat vat03 = vatRepository.findByLabelIgnoreCase("TVA normale");
			
			

			Product produit01 = new Product("Sushi saumon x2", 4.09f, "assets/images/sushiSaumonx2.jpeg",
					"Plat japonais composé d'un riz vinaigré appelé shari combiné avec du saumon cru de l'Atlantique");
			
			produit01.setCategory(category01);
			
			Product produit02 = new Product("Porc Teriyaki", 12.27f, "assets/images/porcTerreIyaki.jpg",
					"Le Teriyaki est une sauce asiatique que l'on trouve toute prête dans les magasins asiatiques au rayon sauce. Elle est composée principalement de sauce soja, gingembre, sucre, eau");
			
			produit02.setCategory(category01);
			
			Product produit03 = new Product("Coca-Cola 33 cl", 1.90f, "assets/images/Coca_Canette_33cl.png",
					"Célèbre boisson mythique");
			
			produit03.setCategory(category03);
			
			
			Product produit04 = new Product("Perrier 33 cl", 1.90f, "assets/images/perrier-33cl.png",
					"Recommandé pour la digestion");
			
			produit04.setCategory(category03);
			
			Product produit05 = new Product("Nouilles sauté au poulet à sauce japonaise", 8.18f,
					"assets/images/bento-poulet-frit.jpg",
					"Les Japonais préparent cette recette avec du bouillon de poulet, de la sauce soja, et surtout du mirin qui va apporter la saveur sucrée qu’on lui connaît bien");
			
			produit05.setCategory(category01);

			Product produit06 = new Product("Salade saumon", 5.27f, "assets/images/salade_saumon_fume.jpg",
					"Salade verte avec pâtes orzo et edamame, mélange de laitues vertes, sauce à aneth, saumon fumé et crudités");
			produit06.setCategory(category00);
			
			Product produit07 = new Product("Salade d'algues", 5f, "assets/images/salade_d_algues.JPG",
					"En plus d'être délicieuses, les algues sont très bonnes pour la santé");

			produit07.setCategory(category00);
			
			Product produit08 = new Product("Perles de coco x2", 2.72f, "assets/images/perle-coco.jpeg",
					"La perle de coco est un dessert chinois à base de farine de riz gluant, fourré à la crème de haricot mungo et au sucre et saupoudré de copeaux de chair de noix de coco");
			
			produit08.setCategory(category02);
			
			Product produit09 = new Product("Nougat lychee", 2.72f, "assets/images/nougat-lychee.jpeg", "Nougat mou sésame");
			
			produit09.setCategory(category02);



			Combo combo01 = new Combo("Menu brochettes MB3", 8.99f, "assets/images/mb3.jpg",
					"assortiment de 5 brochettes ou 4 brochettes de roulés de boeuf au fromage, accompagnés d'une soupe Miso et d'une salade de chou");

			ComboCategory comboCategory01 = new ComboCategory(1);
			ComboCategory comboCategory02 = new ComboCategory(2);

			comboCategory01.setCombo(combo01);
			comboCategory02.setCombo(combo01);

			comboCategory01.setCategory(category101);
			comboCategory02.setCategory(category102);
			
			
			
			
			
			
			
			
			//***************à enregistrer début *********************************
			
			Product prodcutCombo01 = new Product ("Soupe Miso et salade de chou japonaise", 2.72f, "assets/images/miso-et-salade.jpg",
					"Le miso, pâte de haricots de soja fermentée et salée, et sa salade de chou japonaise");
			prodcutCombo01.setCategory(category04);
			prodcutCombo01.setVat(vat02);
			
			Product prodcutCombo02 = new Product("Assortiment de 5 brochettes", 5.45f, "assets/images/5-brochettes.jpeg",
							"1 au boeuf, 1 au poulet, 1 au fromage, 1 aux boulettes de poulet, 1 aux ailes de poulet");
			prodcutCombo02.setCategory(category04);
			prodcutCombo02.setVat(vat02);
			
			Product prodcutCombo03 = new Product("4 brochettes de roulés de boeuf au fromage", 5.45f, "assets/images/brochettes-boeuf-fromage.jpg",
					"Brochettes de boeuf au fromage - Yakitori");
			prodcutCombo03.setCategory(category04);
			prodcutCombo03.setVat(vat02);
			
			Set produts01 = new HashSet<Product>() {
				{
				add(prodcutCombo01);
				}
			};
			
			Set produts02 = new HashSet<Product>() {
				{
				add(prodcutCombo02);
				}
				{
				add(prodcutCombo03);
				}
			};	
			
			comboCategory01.setProducts(produts01);
			comboCategory02.setProducts(produts02);
			

//			Set produts01 = new HashSet<Product>() {
//				{
//					//ok
//					add(new Product("Soupe Miso et salade de chou japonaise", 2.72f, "assets/images/miso-et-salade.jpg",
//							"Le miso, pâte de haricots de soja fermentée et salée, et sa salade de chou japonaise",
//							category04, vat02));
//				}
//			};
			
			

//			Set produts02 = new HashSet<Product>() {
//				{
//					//ok
//					add(new Product("Assortiment de 5 brochettes", 5.45f, "assets/images/5-brochettes.jpeg",
//							"1 au boeuf, 1 au poulet, 1 au fromage, 1 aux boulettes de poulet, 1 aux ailes de poulet",
//							category04, vat02));
//					//ok
//					add(new Product("4 brochettes de roulés de boeuf au fromage", 5.45f, "assets/images/brochettes-boeuf-fromage.jpg",
//							"Brochettes de boeuf au fromage - Yakitori",
//							category04, vat02));
//				}
//			};
//			
			


			
			
			

			Combo combo02 = new Combo("Menu M10", 16.99f, "assets/images/mb10.png",
					"8 sushi saumon ou thon accompagnés d'une soupe Miso et d'une salade de chou... avec un excellent dessert");
			
			
			Product prodcutCombo04 = new Product("8 sushi saumon", 10.91f, "assets/images/8-sushi-saumon.jpeg",
					"Plat japonais composé d'un riz vinaigré appelé shari combiné avec du saumon cru de l'Atlantique");
			prodcutCombo04.setCategory(category04);
			prodcutCombo04.setVat(vat02);
			
			Product prodcutCombo05 = new Product("8 sushi thon", 10.91f, "assets/images/8-sushi-thon.jpg",
					"Plat japonais composé d'un riz vinaigré appelé shari combiné avec du thon cru de l'Atlantique");
			prodcutCombo05.setCategory(category04);
			prodcutCombo05.setVat(vat02);
			
			Set produts04 = new HashSet<Product>() {
				{
				add(prodcutCombo04);
				}
				{
				add(prodcutCombo05);
				}
			};
			
			
			Product prodcutCombo07 = new Product("Perles de coco x2", 1.81f, "assets/images/perle-coco.jpeg",
					"La perle de coco est un dessert chinois à base de farine de riz gluant, fourré à la crème de haricot mungo et au sucre et saupoudré de copeaux de chair de noix de coco");
			prodcutCombo07.setCategory(category04);
			prodcutCombo07.setVat(vat02);
			
			Product prodcutCombo08 = new Product("Nougat lychee", 1.81f, "assets/images/nougat-lychee.jpeg",
					"Nougat mou sésame");
			prodcutCombo08.setCategory(category04);
			prodcutCombo08.setVat(vat02);
			
			Set produts05 = new HashSet<Product>() {
				{
					add(prodcutCombo07);
				}
				{
					add(prodcutCombo08);
				}
			};
			
			//***************à enregistrer fin *********************************
			
			

//			Set produts04 = new HashSet<Product>() {
//				{
//					//ok
//					add(new Product("8 sushi saumon", 10.91f, "assets/images/8-sushi-saumon.jpeg",
//							"Plat japonais composé d'un riz vinaigré appelé shari combiné avec du saumon cru de l'Atlantique",
//							category04, vat02));
//					//ok
//					add(new Product("8 sushi thon", 10.91f, "assets/images/8-sushi-thon.jpg",
//							"Plat japonais composé d'un riz vinaigré appelé shari combiné avec du thon cru de l'Atlantique",
//							category04, vat02));
//				}
//			};

//			Set produts05 = new HashSet<Product>() {
//				{
//					//ok
//					add(new Product("Perles de coco x2", 1.81f, "assets/images/perle-coco.jpeg",
//							"La perle de coco est un dessert chinois à base de farine de riz gluant, fourré à la crème de haricot mungo et au sucre et saupoudré de copeaux de chair de noix de coco",
//							category04, vat02));
//					//ok
//					add(new Product("Nougat lychee", 1.81f, "assets/images/nougat-lychee.jpeg",
//							"Nougat mou sésame",
//							category04, vat02));
//				}
//			};

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
			

			Statut statut010 = new Statut(1, "libre");
			Statut statut020 = new Statut(2, "réservé");

			Statut statut030 = new Statut(3, "commande en cours");
			Statut statut040 = new Statut(4, "commande validée");
			Statut statut050 = new Statut(5, "commande réglée");
			Statut statut060 = new Statut(6, "commande annulée");

			Statut statut070 = new Statut(7, "produit en cours de commande");
			Statut statut080 = new Statut(8, "produit commandé");
			Statut statut090 = new Statut(9, "produit en préparation");
			Statut statut100 = new Statut(10, "produit préparation ok");
			Statut statut110 = new Statut(11, "produit servi");

			Statut statut120 = new Statut(12, "menu en cours de commande");
			Statut statut130 = new Statut(13, "menu commandé");
			
			Statut statut140 = new Statut(14, "annulé");
			
			
			statutRepository.save(statut010);
			statutRepository.save(statut020);
			statutRepository.save(statut030);
			statutRepository.save(statut040);
			statutRepository.save(statut050);
			statutRepository.save(statut060);

			statutRepository.save(statut070);
			statutRepository.save(statut080);
			statutRepository.save(statut090);
			statutRepository.save(statut100);
			statutRepository.save(statut110);

			statutRepository.save(statut120);
			statutRepository.save(statut130);
			statutRepository.save(statut140);
			
			Statut statut01 = statutRepository.findByNumero(1);
			Statut statut02 = statutRepository.findByNumero(2);
			Statut statut03 = statutRepository.findByNumero(3);
			Statut statut04 = statutRepository.findByNumero(4);
			Statut statut05 = statutRepository.findByNumero(5);
			Statut statut06 = statutRepository.findByNumero(6);
			Statut statut07 = statutRepository.findByNumero(7);
			Statut statut08 = statutRepository.findByNumero(8);
			Statut statut09 = statutRepository.findByNumero(9);
			Statut statut10 = statutRepository.findByNumero(10);
			Statut statut11 = statutRepository.findByNumero(11);
			Statut statut12 = statutRepository.findByNumero(12);
			Statut statut13 = statutRepository.findByNumero(13);
			Statut statut14 = statutRepository.findByNumero(14);
			
			

			table01.setStatut(statut01);
			table02.setStatut(statut01);
			table03.setStatut(statut01);
			table04.setStatut(statut01);
			table05.setStatut(statut01);
			table06.setStatut(statut02);
			table07.setStatut(statut01);
			table08.setStatut(statut01);
			table09.setStatut(statut01);
			table10.setStatut(statut01);


			MyUser myUser01 = new MyUser("jeannory.phou@gmail.com","theBoss", "jeannory", "phou");
			MyUser myUser02 = new MyUser("terreiyaki@gmail.com","theKing", "terre", "iyaki");

			MyGrant myGrant010 = new MyGrant ("client");
			MyGrant myGrant020 = new MyGrant ("serveur");
			MyGrant myGrant030 = new MyGrant ("cuisinier");
			MyGrant myGrant040 = new MyGrant ("caissier");
			MyGrant myGrant050 = new MyGrant ("administrateur");
			


			myGrantRepository.save(myGrant010);
			myGrantRepository.save(myGrant020);
			myGrantRepository.save(myGrant030);
			myGrantRepository.save(myGrant040);
			myGrantRepository.save(myGrant050);
			
			MyGrant myGrant01=myGrantRepository.findByName("client");
			MyGrant myGrant02=myGrantRepository.findByName("serveur");
			MyGrant myGrant03=myGrantRepository.findByName("cuisinier");
			MyGrant myGrant04=myGrantRepository.findByName("caissier");
			MyGrant myGrant05=myGrantRepository.findByName("administrateur");

			Set myGrants01 = new HashSet<MyGrant>() {
				{
				add(myGrant02);
				add(myGrant03); 
				add(myGrant04);
				add(myGrant05);
				}
				};

			Set myGrants02 = new HashSet<MyGrant>() {{
				add(myGrant02);
				add(myGrant03); 
				add(myGrant04);
			}};
			
			myGrantRepository.save(myGrants01);
			myGrantRepository.save(myGrants02);

			myUser01.setMyGrants(myGrants01);
			myUser02.setMyGrants(myGrants02);

			CategoryMessage categoryMessage010 = new CategoryMessage (1, "succès");
			CategoryMessage categoryMessage020 = new CategoryMessage (2, "erreur");
			

			categoryMessageRepository.save(categoryMessage010);
			categoryMessageRepository.save(categoryMessage020);
			
			CategoryMessage categoryMessage01 =categoryMessageRepository.findByNumber(1);
			CategoryMessage categoryMessage02 =categoryMessageRepository.findByNumber(2);

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

			TheMessage theMessage12 = new TheMessage(12,"Menu ajouté");
			TheMessage theMessage14 = new TheMessage(14,"Menu supprimé");

			TheMessage theMessage15 = new TheMessage(15,"Commande validée");

			TheMessage theMessage16 = new TheMessage(16,"Vous devez choisir une table disponible");
			TheMessage theMessage17 = new TheMessage(17,"Votre commande a été annulée");
			
			TheMessage theMessage18 = new TheMessage(18,"Jeu essai 01 ok");
			TheMessage theMessage19 = new TheMessage(19,"Jeu essai 02 ok");
			
			TheMessage theMessage20 = new TheMessage(20,"Vous devez choisir choisir une table avant de valider");

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

			theMessage12.setCategoryMessage(categoryMessage01);

			theMessage14.setCategoryMessage(categoryMessage01);
			theMessage15.setCategoryMessage(categoryMessage01);
			theMessage16.setCategoryMessage(categoryMessage01);
			theMessage17.setCategoryMessage(categoryMessage01);

			theMessage18.setCategoryMessage(categoryMessage01);
			theMessage19.setCategoryMessage(categoryMessage01);
			
			theMessage20.setCategoryMessage(categoryMessage02);
			
			OrderType orderType01 = new OrderType ("Sur place");
			OrderType orderType02 = new OrderType ("A emporter");

			productRepository.save(produts01);
			productRepository.save(produts02);	
			productRepository.save(produts04);
			productRepository.save(produts05);



			productRepository.save(produit01);
			productRepository.save(produit02);
			productRepository.save(produit03);
			productRepository.save(produit04);
			productRepository.save(produit05);
			productRepository.save(produit06);
			productRepository.save(produit07);
			productRepository.save(produit08);
			productRepository.save(produit09);
			
			productRepository.save(prodcutCombo01);
			productRepository.save(prodcutCombo02);
			productRepository.save(prodcutCombo03);
			productRepository.save(prodcutCombo04);
			productRepository.save(prodcutCombo05);
			productRepository.save(prodcutCombo07);
			productRepository.save(prodcutCombo08);
			


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

			myUserRepository.save(myUser01);
			myUserRepository.save(myUser02);


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

			theMessageRepository.save(theMessage12);
			theMessageRepository.save(theMessage14);

			theMessageRepository.save(theMessage15);

			theMessageRepository.save(theMessage16);
			theMessageRepository.save(theMessage17);
			
			theMessageRepository.save(theMessage18);
			theMessageRepository.save(theMessage19);

			theMessageRepository.save(theMessage20);
			
			orderTypeRepository.save(orderType01);
			orderTypeRepository.save(orderType02);
			
			return theMessage18;
			//*********************fin jeu d'essai 01 *********************************
		}

		
		public TheMessage jeuEssai02()  {
			
			System.out.println("\n*********************ajout d'une bierre et d'un menu début ****************");

	//ajout d'une bierre
			
			Category cat10 = categoryRepository.findByNameIgnoreCase("Boissons");

			Vat vat00 = vatRepository.findByLabelIgnoreCase("TVA réduite");
			Vat vat10 = vatRepository.findByLabelIgnoreCase("TVA intermédiaire");
			Vat vat20 = vatRepository.findByLabelIgnoreCase("TVA normale");

			Product produit10 = new Product("HEINEKEN - ", 3.33f, "assets/images/verre-biere-heieneken-standard_25cl.jpg", "Bière blonde 5° - 25 cl", cat10);

//			produit10.setCategory(cat10);
			
			produit10.setVat(vat20);
			
			

	//ajout d'un nouveau menu
			
			Category cat11 = categoryRepository.findByNameIgnoreCase("1 - Entrées");
			Category cat12 = categoryRepository.findByNameIgnoreCase("2 - Plats au choix");
			Category cat13 = categoryRepository.findByNameIgnoreCase("3 - Desserts au choix");
			Category cat14 = new Category("4 - Boissons au choix");
			
			
			categoryRepository.save(cat14);	

					Combo combo10 = new Combo("Menu Bateau Yoki", 50f, "assets/images/bateau-or-plat.jpg",
							"Menu royal pour 2 personnes");
					
					//5 soit 4.55
					Product prodCombo01 = new Product("2 Soupe Miso et 2 salades de chou japonaise", 4.55f, "assets/images/miso-et-salade.jpg",
							"Le miso, pâte de haricots de soja fermentée et salée, et sa salade de chou japonaise pour 2");
					prodCombo01.setCategory(cat11);
					prodCombo01.setVat(vat10);
					
					Set produts10 = new HashSet<Product>() {
						{
							add(prodCombo01);
						}
					};

			//5 soit 4.55
//					Set produts10 = new HashSet<Product>() {
//						{
//							add(new Product("2 Soupe Miso et 2 salades de chou japonaise", 4.55f, "assets/images/miso-et-salade.jpg",
//									"Le miso, pâte de haricots de soja fermentée et salée, et sa salade de chou japonaise pour 2",
//									cat12, vat10));
//						}
//				    };

					
					
					Product prodCombo02 = new Product("6 Sushi,6 maki,6 california maki,15 assortiment de sashimi 8 Brochettes", 32.73f, "assets/images/bateau-or-plat.jpg",
							"6 Sushi,6 maki,6 california maki,15 assortiment de sashimi 8 Brochettes");
					
					prodCombo02.setCategory(cat12);
					prodCombo02.setVat(vat10);
					
					Product prodCombo03 = new Product("8 california thon avocat + 8 saumon, 8 sushi assortiment + 18 sashimi, 6 Pièces de raviolis", 32.73f, "assets/images/bateau-or-plat-2.jpg",
							"8 california thon avocat + 8 saumon, 8 sushi assortiment + 18 sashimi, 6 Pièces de raviolis");
					prodCombo03.setCategory(cat12);
					prodCombo03.setVat(vat10);
					
					//36 soit 32.73
					Set produts20 = new HashSet<Product>() {
						{
							add(prodCombo02);
						}
						{
							add(prodCombo03);
						}
					};
					
			//36 soit 32.73
//					Set produts20 = new HashSet<Product>() {
//						{
//							add(new Product("6 Sushi,6 maki,6 california maki,15 assortiment de sashimi 8 Brochettes", 32.73f, "assets/images/bateau-or-plat.jpg",
//									"6 Sushi,6 maki,6 california maki,15 assortiment de sashimi 8 Brochettes",
//									cat12, vat10));
//							add(new Product("8 california thon avocat + 8 saumon, 8 sushi assortiment + 18 sashimi, 6 Pièces de raviolis", 32.73f, "assets/images/bateau-or-plat-2.jpg",
//									"8 california thon avocat + 8 saumon, 8 sushi assortiment + 18 sashimi, 6 Pièces de raviolis",
//									cat12, vat10));
//						}
//				    };

					
					Product prodCombo04 = new Product("2 boules de glaces", 4.55f, "assets/images/glace-deux-boules.jpg",
							"Délicieux dessert glacé");
					prodCombo04.setCategory(cat13);
					prodCombo04.setVat(vat10);
					
					Product prodCombo05 = new Product("2 cafés", 4.55f, "assets/images/2-expressos.jpg",
							"2 expressos");
					prodCombo05.setCategory(cat13);
					prodCombo05.setVat(vat10);
					
					Product prodCombo06 = new Product("1 café et 1 boule de glace", 4.55f, "assets/images/Image_Non_Disponible.jpg",
							"1 café et 1 boule de glace");
					prodCombo06.setCategory(cat13);
					prodCombo06.setVat(vat10);
					
					//5 soit 4.55
					Set produts30 = new HashSet<Product>() {
						{
							add(prodCombo04);
						}
						{
							add(prodCombo05);
						}
						{
							add(prodCombo06);
						}
					};

//			//5 soit 4.55
//					Set produts30 = new HashSet<Product>() {
//						{
//							add(new Product("2 boules de glaces", 4.55f, "assets/images/glace-deux-boules.jpg",
//									"Délicieux dessert glacé",
//									cat13, vat10));
//							add(new Product("2 cafés", 4.55f, "assets/images/2-expressos.jpg",
//									"2 expressos",
//									cat13, vat10));
//							add(new Product("1 café et 1 boule de glace", 4.55f, "assets/images/Image_Non_Disponible.jpg",
//									"1 café et 1 boule de glace",
//									cat13, vat10));
//						}
//				    };

				    
					Product prodCombo07 = new Product("2 flutes de champagne", 3.33f, "assets/images/flute-a-champagne.jpg",
							"2 flutes de champagne");
					
					prodCombo07.setCategory(cat14);
					prodCombo07.setVat(vat20);
					
					Product prodCombo08 = new Product("2 cannettes gazeux au choix (sans alcool)", 3.79f, "assets/images/Coca_Canette_33cl.png",
							"2 cannettes gazeux au choix (sans alcool)");
					prodCombo08.setCategory(cat14);
					prodCombo08.setVat(vat00);
					
					//4==> 3.79 pour 5.5
					//4==> 3.33 pour 20	
					Set produts40 = new HashSet<Product>() {
						{
							add(prodCombo07);
						}
						{
							add(prodCombo08);
						}
					};
					
			//4==> 3.79 pour 5.5
			//4==> 3.33 pour 20
//					Set produts40 = new HashSet<Product>() {
//						{
//							add(new Product("2 flutes de champagne", 3.33f, "assets/images/flute-a-champagne.jpg",
//									"2 flutes de champagne",
//									cat14, vat20));
//							add(new Product("2 cannettes gazeux au choix (sans alcool)", 3.79f, "assets/images/Coca_Canette_33cl.png",
//									"2 cannettes gazeux au choix (sans alcool)",
//									cat14, vat00));
//						}
//					};

			
					ComboCategory comboCategory01 = new ComboCategory(1);
					ComboCategory comboCategory02 = new ComboCategory(2);
					ComboCategory comboCategory03 = new ComboCategory(3);
					ComboCategory comboCategory04 = new ComboCategory(4);
					
	
					
					
					
					
					comboCategory01.setCombo(combo10);
					comboCategory02.setCombo(combo10);
					comboCategory03.setCombo(combo10);
					comboCategory04.setCombo(combo10);

					comboCategory01.setCategory(cat11);
					comboCategory02.setCategory(cat12);
					comboCategory03.setCategory(cat13);
					comboCategory04.setCategory(cat14);

					comboCategory01.setProducts(produts10);
					comboCategory02.setProducts(produts20);
					comboCategory03.setProducts(produts30);
					comboCategory04.setProducts(produts40);
					
		
					
					comboRepository.save(combo10);
					
					productRepository.save(produit10);
					
					productRepository.save(prodCombo01);
					productRepository.save(prodCombo02);
					productRepository.save(prodCombo03);
					
					productRepository.save(prodCombo04);
					productRepository.save(prodCombo05);
					productRepository.save(prodCombo06);
					productRepository.save(prodCombo07);
					productRepository.save(prodCombo08);
					
					productRepository.save(produts10);
					productRepository.save(produts20);
					productRepository.save(produts30);
					productRepository.save(produts40);
					
		
					
					comboCategoryRepository.save(comboCategory01);
					comboCategoryRepository.save(comboCategory02);
					comboCategoryRepository.save(comboCategory03);
					comboCategoryRepository.save(comboCategory04);

				
					
					System.out.println("\n*********************ajout d'une bierre et d'un menu fin ****************");
		
					return theMessageRepository.findByNumber(19);
		}
		
		

}
