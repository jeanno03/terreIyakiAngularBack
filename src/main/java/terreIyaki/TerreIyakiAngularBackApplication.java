package terreIyaki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import terreIyaki.entity.*;
import terreIyaki.repository.*;

@SpringBootApplication
public class TerreIyakiAngularBackApplication extends SpringBootServletInitializer {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private VatRepository vatRepository;




	public static void main(String[] args) {
		SpringApplication.run(TerreIyakiAngularBackApplication.class, args);
	}

	@Transactional
	public void run(String... arg0) throws Exception {


		productRepository.findAll().forEach(System.out::println);
		categoryRepository.findAll().forEach(System.out::println);
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