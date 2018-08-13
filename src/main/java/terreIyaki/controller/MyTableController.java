package terreIyaki.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import terreIyaki.entity.MyTable;
import terreIyaki.repository.MyTableRepository;

@RestController
public class MyTableController {

	private MyTableRepository myTableRepository;

	public MyTableController(MyTableRepository myTableRepository) {
		super();
		this.myTableRepository = myTableRepository;
	}

	@RequestMapping(value = "/getMyTableById", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public MyTable getComboCategoryById(@RequestParam(name="id", defaultValue="0L")Long id) {
		return myTableRepository.findById(id);
	}

}
