package terreIyaki.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import terreIyaki.entity.Combo;
import terreIyaki.repository.ComboRepository;

@RestController
@CrossOrigin("*")
public class ComboController {

	private ComboRepository comboRepository;

	public ComboController(ComboRepository comboRepository) {
		super();
		this.comboRepository = comboRepository;
	}

	@RequestMapping(value = "/getComboByName", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public Combo getComboByName(@RequestParam(name="name", defaultValue="")String name) {
		return comboRepository.findByNameIgnoreCase(name);
	}

}
