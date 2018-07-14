package terreIyaki.controller;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import terreIyaki.entity.Category;
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

	
	
	@RequestMapping(value = "/getProductByName", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public List<Product> getProductByName(@RequestParam(name="name", defaultValue="")String name){
		return productRepository.findByNameIgnoreCase(name);
	}
	
//	@RequestMapping(value = "/getProductByTheComboCategoryId", method = RequestMethod.GET)
//	@CrossOrigin(origins = "*")
//	public List<Product> getProductByTheComboCategoryId(@RequestParam(name="id", defaultValue="")Long id){
//		return productRepository.findByTheComboCategoryId(id);
//	}
//	



}
