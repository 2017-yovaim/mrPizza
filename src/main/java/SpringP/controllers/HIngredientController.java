package SpringP.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import SpringP.models.Ingredient;
import SpringP.repo.IIngredientRepository;

@Controller
@RequestMapping("h/ingredients")
public class HIngredientController 
{
	@Autowired
	private IIngredientRepository repo;
	
	@GetMapping("/index")
	public String index(Model model)
	{
		model.addAttribute("ingredientsList", repo.selectAll());
		return "index";
	}
	
	@GetMapping("/add")
	public String add(Model model)
	{
		model.addAttribute("toAdd", new Ingredient());
		//add((Ingredient)model.getAttribute("toAdd"));
		return "fIngredient";
	}
	
	@PostMapping("/add")
	public String add(Ingredient toAdd)
	{
		repo.add(toAdd);
		return "redirect:/h/ingredients/index";
	}
	
	
	
	@GetMapping("/{id}")
	public String getByID(Model model, @PathVariable Long id)
	{
		model.addAttribute("ingredient", repo.getByID(id));
		return "ingredient";
	}

	
	
}
