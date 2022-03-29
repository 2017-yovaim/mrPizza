package SpringP.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SpringP.models.Pizza;
import SpringP.repo.IPizzaRepo;

@RestController
@RequestMapping("j/pizzas")
public class JPizzaController 
{
	@Autowired
	private IPizzaRepo repo;
	
	@GetMapping("/all")
	public List<Pizza> selectAll()
	{
		return repo.selectAll();
	}
}
