package terreIyaki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.NonNull;

import java.util.HashSet;
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
		Product produit05 = new Product("Riz sauté au poulet à sauce japonaise", 8.18f,
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
		
		//******************Menu 1 ******************************//
		
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
		
		//********************many product début ****************************

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
		
		
		//********************many product fin ****************************
		
		//********************one product début ****************************
//		Product pc01 = new Product ("Soupe Miso et salade de chou japonaise", 2.72f, "assets/images/miso-et-salade.jpg","Le miso, pâte de haricots de soja fermentée et salée, et sa salade de chou japonaise",category04, vat02);
//		
//		
//		Product pc02 = new Product ("Assortiment de 5 brochettes", 5.45f, "assets/images/5-brochettes.jpeg", "1 au boeuf, 1 au poulet, 1 au fromage, 1 aux boulettes de poulet, 1 aux ailes de poulet",category04, vat02);
//		Product pc03 = new Product ("4 brochettes de roulés de boeuf au fromage", 5.45f, "assets/images/brochettes-boeuf-fromage.jpg","Brochettes de boeuf au fromage - Yakitori",category04, vat02);
//		
//		pc01.setComboCategory(comboCategory01);
//		pc02.setComboCategory(comboCategory02);
//		pc03.setComboCategory(comboCategory02);
		
		
		
		//********************one product fin ****************************
		
		//******************Menu 2 ******************************//
		
		Combo combo02 = new Combo("Menu M10", 15.45f, "assets/images/mb10.png",
				"8 sushi saumon ou thon accompagnés d'une soupe Miso et d'une salade de chou... avec un excellent dessert");

		//********************many product début ****************************
		
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
		//********************many product fin ****************************
		
		ComboCategory comboCategory03 = new ComboCategory(1);
		ComboCategory comboCategory04 = new ComboCategory(2);
		ComboCategory comboCategory05 = new ComboCategory(3);
		
		
		comboCategory03.setCombo(combo02);
		comboCategory04.setCombo(combo02);
		comboCategory05.setCombo(combo02);

		comboCategory03.setCategory(category101);
		comboCategory04.setCategory(category102);
		comboCategory05.setCategory(category103);
		
		//********************many product début ****************************
		comboCategory03.setProducts(produts01);
		comboCategory04.setProducts(produts04);
		comboCategory05.setProducts(produts05);
		//********************many product fin ****************************
		
		//********************one product début ****************************
		
//		Product pc04 = new Product ("Soupe Miso et salade de chou japonaise", 2.72f, "assets/images/miso-et-salade.jpg","Le miso, pâte de haricots de soja fermentée et salée, et sa salade de chou japonaise",category04, vat02);
//		
//		Product pc05 = new Product ("8 sushi saumon", 10.91f, "assets/images/8-sushi-saumon.jpeg","Plat japonais composé d'un riz vinaigré appelé shari combiné avec du saumon cru de l'Atlantique",category04, vat02);
//		Product pc06 = new Product ("8 sushi thon", 10.91f, "assets/images/8-sushi-thon.jpg","Plat japonais composé d'un riz vinaigré appelé shari combiné avec du thon cru de l'Atlantique",category04, vat02);
//		
//		Product pc07 = new Product ("Perles de coco x2", 1.81f, "assets/images/perle-coco.jpeg","La perle de coco est un dessert chinois à base de farine de riz gluant, fourré à la crème de haricot mungo et au sucre et saupoudré de copeaux de chair de noix de coco",category04, vat02);
//		Product pc08 = new Product ("Nougat lychee", 1.81f, "assets/images/nougat-lychee.jpeg","Nougat mou sésame",category04, vat02);
//		
//		pc04.setComboCategory(comboCategory03);
//		
//		pc05.setComboCategory(comboCategory04);
//		pc06.setComboCategory(comboCategory04);
//		
//		pc07.setComboCategory(comboCategory05);
//		pc08.setComboCategory(comboCategory05);
		
		//********************one product fin ****************************
		
		//************************************************//
		
		produit01.setVat(vat02);
		produit02.setVat(vat02);
		produit03.setVat(vat01);
		produit04.setVat(vat01);
		produit05.setVat(vat02);

		produit06.setVat(vat02);
		produit07.setVat(vat02);

		produit08.setVat(vat02);
		produit09.setVat(vat02);
		//********************many product début ****************************
		productRepository.save(produts01);
		productRepository.save(produts02);	
		productRepository.save(produts04);
		productRepository.save(produts05);
		//********************many product fin ****************************
		
		//********************one product début ****************************
//		productRepository.save(pc01);
//		productRepository.save(pc02);
//		productRepository.save(pc03);
//		productRepository.save(pc04);
//		productRepository.save(pc05);
//		productRepository.save(pc06);
//		productRepository.save(pc07);
//		productRepository.save(pc08);
		//********************one product fin ****************************

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

		productRepository.findAll().forEach(System.out::println);
		CategoryRepository.findAll().forEach(System.out::println);
		vatRepository.findAll().forEach(System.out::println);
	}
}