package terreIyaki.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import terreIyaki.repository.CategoryRepository;
import terreIyaki.entity.Category;

@RestController
@CrossOrigin("*")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryController(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}
	
	@RequestMapping(value = "/getCategoryByName", method = RequestMethod.GET)
	public List<Category> getCategoryByName(@RequestParam(name="name", defaultValue="Plats")String name){
		return categoryRepository.findByNameIgnoreCase(name);
	}
	
//	@RequestMapping(value = "/getCarsList2", method = RequestMethod.GET)
//	public List<Car> getListCar2(@RequestParam(name="name", defaultValue="Ferrari")String name){
//		return carRepository2.findByNameIgnoreCase(name);
//	}
	
	@GetMapping("/getCategoryById")
	@CrossOrigin(origins = "*")
	public Category getCategoryById() {
		
		return categoryRepository.findOne(2l);
	}
	
	
}
