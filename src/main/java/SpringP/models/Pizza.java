package SpringP.models;

import java.util.List;

public class Pizza 
{
	private Long id;
	private String name;
	private double price;
	private List<Ingredient> ingredients;
	
	public Long getId() 
	{
		return id;
	}
	
	public void setId(Long id) 
	{
		this.id = id;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public double getPrice() 
	{
		return price;
	}
	
	public void setPrice(double price) 
	{
		if(price > 0)
			this.price = price;
		else
		{
			throw new IllegalArgumentException("Negative price value in Pizza");
		}
	}
	
	public List<Ingredient> getIngredients() 
	{
		return ingredients;
	}
	
	public void setIngredients(List<Ingredient> ingredients) 
	{
		this.ingredients = ingredients;
	}
	
	public Pizza(Long id, String name, double price, List<Ingredient> ingredients)
	{
		this.setId(id);
		this.setName(name);
		this.setPrice(price);
		this.setIngredients(ingredients);
	}
	
	public Pizza()
	{
		this(0L, "None pizza left beef", 5.40, List.of(new Ingredient(), 
				new Ingredient(4L, "Tomato sause")));
	}
	
	@Override
	public String toString()
	{
		return String.format("Pizza: %s, Price: %f, Ingredients: %s", this.getName(),
				this.getPrice(), this.getIngredients().toString());
	}
	
	public boolean exist(String name)
	{
		for(Ingredient i : ingredients)
			if(i.getName().equals(name))
				return true;
		return false;
	}
	
	
	
}
