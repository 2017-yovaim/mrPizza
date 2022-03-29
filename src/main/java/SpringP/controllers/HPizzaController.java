package SpringP.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import SpringP.models.Pizza;
import SpringP.repo.IIngredientRepository;
import SpringP.repo.IPizzaRepo;

@Controller
@RequestMapping("h/pizzas")
public class HPizzaController 
{
	@Autowired
	private IPizzaRepo repo;
	
	@Autowired
	private IIngredientRepository ingredientRepo; //I think I need it to add ingredients
	
	@GetMapping("/index")
	public String index(Model model)
	{
		model.addAttribute("pizzaList", repo.selectAll());
		return "indexPizza";
	}
	
	@GetMapping("/{id}")
	public String getByID(Model model, @PathVariable Long id)
	{
		model.addAttribute("pizza", repo.getByID(id));
		return "pizza";
	}
	
	@GetMapping("/add")
	public String add(Model model)
	{
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("ingredientsList", ingredientRepo.selectAll());
		return "fPizza";
	}
	
	@PostMapping("/add")
	public String add(Pizza toAdd)
	{
		repo.add(toAdd);
		return "redirect:/h/pizzas/index";
	}
}
