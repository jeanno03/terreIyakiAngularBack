package terreIyaki.service;

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
	
}
