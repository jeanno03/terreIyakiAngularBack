package terreIyaki.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import terreIyaki.entity.Product;
import terreIyaki.repository.*;

@RestController
public class ProductController {

	private ProductRepository productRepository;

	public ProductController(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@GetMapping("/getProducts")
	@CrossOrigin(origins = "*")
	public Collection<Product> getProducts() {
		return productRepository.findAll();
	}	

	//attention cette requete va retourner plusieurs objects
	@RequestMapping(value = "/getProductByName", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public List<Product> getProductByName(@RequestParam(name="name", defaultValue="")String name){
		return productRepository.findByNameIgnoreCase(name);
	}

	@RequestMapping(value = "/getProductById", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public Product findProductbyId(Long id) {
		return productRepository.findById(id);
	}

}
