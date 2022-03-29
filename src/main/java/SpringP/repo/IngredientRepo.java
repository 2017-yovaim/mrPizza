package SpringP.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import SpringP.models.Ingredient;

@Repository
public class IngredientRepo implements IIngredientRepository
{
	
	private Map<Long, Ingredient> ingredients = new HashMap<>();
	private Long id = 0L;
	
	@Autowired
	private JdbcTemplate connection;
	
	
	@Override
	public void add(Ingredient ingredient) 
	{
		id++;
		//ingredient.setId(++id);
		ingredients.put(id, ingredient);
		connection.update("INSERT INTO Ingredients(Name) VALUES(?)", 
				ingredient.getName());
		
	}

	@Override
	public List<Ingredient> selectAll() 
	{
		List<Ingredient> result = connection.query("SELECT * FROM Ingredients",
				new RowMapper<Ingredient>() 
		{

					@Override
					public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException 
					{
						Ingredient toReturn = new Ingredient();
						toReturn.setId(rs.getLong("ID"));
						toReturn.setName(rs.getString("Name"));
						return toReturn;
					}
			
		});
		return result;
		//return new ArrayList<Ingredient>(ingredients.values());
	}

	@Override
	public Ingredient getByID(Long id) 
	{
		/*List<Ingredient> results = connection.query("SELECT * FROM Ingredients",
				new RowMapper<Ingredient>() {

					@Override
					public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
						Ingredient toReturn = new Ingredient();
						toReturn.setId(rs.getLong("ID"));
						toReturn.setName(rs.getString("Name"));
						return toReturn;
					}
			
		});
		*/
		List<Ingredient> results = selectAll();
		for(Ingredient i : results)
		{
			if(i.getId() == id)
				return i;
		}
		
		return null;
		
	}

}
