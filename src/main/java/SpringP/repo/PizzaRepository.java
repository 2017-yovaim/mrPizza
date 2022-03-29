package SpringP.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import SpringP.models.Ingredient;
import SpringP.models.Pizza;

@Repository
public class PizzaRepository implements IPizzaRepo
{
	@Autowired
	private JdbcTemplate connection;

	@Override
	public List<Pizza> selectAll() 
	{
		List<Pizza> pizzas = new ArrayList<Pizza>(connection.query("SELECT * FROM Pizzas", 
				new RowMapper<Pizza>() 
		{

					@Override
					public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException 
					{
						Pizza toReturn = new Pizza();
						toReturn.setId(rs.getLong("ID"));
						toReturn.setName(rs.getString("Name"));
						toReturn.setPrice(rs.getDouble("Price"));
						return toReturn;
					}
			
		}));
		
		for(Pizza pizza : pizzas)
		{
			addIngredientsToPizzaModel(pizza);
		}
		
		return pizzas;
	}
	
	private void addIngredientsToPizzaModel(Pizza pizza)
	{
		List<Ingredient> ingredients = new ArrayList<>(connection.query(new PreparedStatementCreator() 
		{

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException 
			{
				String idString = Long.toString(pizza.getId());
				//int idInt = Integer.parseInt(idString);
				PreparedStatement p = con.prepareStatement("select * from ingredients inner join ingredients_pizzas on ingredients.id = ingredients_pizzas.ingredientid where pizzaid = ?");
				p.setString(1, idString);				
				return p;
				
			}
			
		}, new RowMapper<Ingredient>() 
		{

			@Override
			public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
				Ingredient toReturn = new Ingredient();
				toReturn.setId(rs.getLong("ID"));
				toReturn.setName(rs.getString("Name"));
				return toReturn;
			}
			
		}));
		
		pizza.setIngredients(ingredients);
	}

	@Override
	public void add(Pizza pizza) 
	{
		connection.update("INSERT INTO Pizzas(Name, Price)VALUES(?, ?)", pizza.getName(),
				pizza.getPrice());
		
	}

	@Override
	public Pizza getByID(Long id) 
	{
		List<Pizza> pizzas = selectAll();
		for(Pizza p : pizzas)
		{
			if(p.getId() == id)
				return p;
		}
		
		return null;
	}
	
	
}
