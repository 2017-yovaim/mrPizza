package SpringP.repo;

import java.util.List;

import SpringP.models.Ingredient;

public interface IIngredientRepository 
{
	void add(Ingredient ingredient);
	List<Ingredient> selectAll();
	Ingredient getByID(Long id);
}
