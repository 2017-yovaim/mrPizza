package SpringP.models;

public class Ingredient 
{
	private Long id;
	private String name;
	
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
	
	public Ingredient(Long id, String name)
	{
		this.setId(id);
		this.setName(name);
	}
	
	public Ingredient()
	{
		this.setName("Beef");
		this.setId(0L);
	}
	
	@Override
	public String toString()
	{
		return this.getName();
	}
}
