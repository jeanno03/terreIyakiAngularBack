package terreIyaki.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import terreIyaki.repository.CategoryRepository;
import terreIyaki.repository.ComboCategoryRepository;
import terreIyaki.repository.ComboRepository;
import terreIyaki.repository.ProductRepository;
import terreIyaki.entity.*;

public class MenuTreatment {
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired	
	private CategoryRepository CategoryRepository;
	
	@Autowired
	private ComboRepository comboRepository;

	@Autowired
	private ComboCategoryRepository comboCategoryRepository;
	
	
	public Map<Integer, Product> getComboDetails(){
		Map ma01 = new HashMap<Integer, Product>();
		
		
		return ma01;
		
	}
	
	


}
