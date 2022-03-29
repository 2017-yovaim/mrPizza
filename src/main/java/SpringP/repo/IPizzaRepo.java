package SpringP.repo;
import java.util.List;
import SpringP.models.Pizza;

public interface IPizzaRepo 
{
	List<Pizza> selectAll();
	void add(Pizza pizza);
	Pizza getByID(Long id);
	
}
