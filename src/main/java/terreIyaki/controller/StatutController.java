package terreIyaki.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import terreIyaki.entity.Statut;
import terreIyaki.repository.StatutRepository;

@RestController
public class StatutController {
	
	private StatutRepository statutRepository;
	
	public StatutController(StatutRepository statutRepository) {
		super();
		this.statutRepository = statutRepository;
	}

	@RequestMapping(value = "/getStatutByName", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	Statut getStatutByName(String name) {
		return statutRepository.getStatutByName(name);
	}

	@RequestMapping(value = "/getStatutByNumero", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	Statut getStatutByNumero(int id) {
		return statutRepository.findByNumero(id);
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
