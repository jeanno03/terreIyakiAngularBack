package terreIyaki.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import terreIyaki.entity.Combo;
import terreIyaki.entity.ComboCategory;
import terreIyaki.repository.ComboCategoryRepository;

@RestController
public class ComboCategoryController {
	
	private ComboCategoryRepository comboCategoryRepository;

	 	public ComboCategoryController(ComboCategoryRepository comboCategoryRepository) {
		super();
		this.comboCategoryRepository = comboCategoryRepository;
	}

		@RequestMapping(value = "/getComboCategoryById", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ComboCategory getComboCategoryById(@RequestParam(name="id", defaultValue="0L")Long id) {
		return comboCategoryRepository.findById(id);
	}


}
