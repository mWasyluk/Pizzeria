package pl.mvasio.pizzeria.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pl.mvasio.pizzeria.Ingredient;
import pl.mvasio.pizzeria.Pizza;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Repository
public class PizzaRepositoryImpl implements PizzaRepository {

    private JdbcTemplate jdbc;
    private IngredientRepository ingredientRepo;

    @Autowired
    public PizzaRepositoryImpl ( JdbcTemplate jdbc, IngredientRepository ingrep ){
        this.jdbc = jdbc;
        this.ingredientRepo = ingrep;
    }

    @Override
    public Pizza add (Pizza pizza) {
        long id = addPizza ( pizza );
        pizza.setId(id);
        for ( String i: pizza.getIngredients()){
            addPizzaIngredients(pizza.getId(), ingredientRepo.getById(i));
        }
        return pizza;
    }

    private long addPizza(Pizza pizza) {
        pizza.setCreateDate( new Date() );
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Pizza (name, date) values (?, ?)", Types.VARCHAR , Types.TIMESTAMP );
        pscf.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                        Arrays.asList( pizza.getName(), new Timestamp(pizza.getCreateDate().getTime()) ));
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(psc, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    private void addPizzaIngredients(Long pizzaId, Ingredient ingredient) {
        jdbc.update(
                "insert into Pizza_Ingredients (pizza_id, ingredient_id) values (?, ?)",
                pizzaId,
                ingredient.getId());
    }
}
