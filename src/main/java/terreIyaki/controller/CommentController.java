package terreIyaki.controller;

import java.sql.SQLException;
import terreIyaki.tool.*;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import terreIyaki.entity.Combo;
import terreIyaki.entity.Comment;
import terreIyaki.entity.MyOrder;
import terreIyaki.repository.CommentRepository;

@RestController
@CrossOrigin("*")
public class CommentController {
	
	private CommentRepository commentRepository;

	public CommentController(CommentRepository commentRepository) {
		super();
		this.commentRepository = commentRepository;
	}
	
//	@RequestMapping(value = "/getComboByName", method = RequestMethod.GET)
//	@CrossOrigin(origins = "*")
//	public Combo getComboByName(@RequestParam(name="name", defaultValue="")String name) {
//		return comboRepository.findByNameIgnoreCase(name);
//	}
	
	@RequestMapping(value = "/countComments", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public PageElement countComments(Long nbParPage){
		Long element = commentRepository.count();
		PageElement pe = new PageElement(element, nbParPage);
		return pe;
	}

}
