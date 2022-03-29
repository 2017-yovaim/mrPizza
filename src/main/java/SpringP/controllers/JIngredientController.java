package SpringP.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SpringP.models.Ingredient;
import SpringP.repo.IIngredientRepository;

@RestController()
@RequestMapping("/j/ingredients")
public class JIngredientController 
{
	@Autowired
	private IIngredientRepository repo;
	
	@GetMapping("/all")
	public List<Ingredient> selectAll()
	{
		return repo.selectAll();
	}
}
