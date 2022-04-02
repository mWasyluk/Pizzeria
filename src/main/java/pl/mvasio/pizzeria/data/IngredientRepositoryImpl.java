package pl.mvasio.pizzeria.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.mvasio.pizzeria.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class IngredientRepositoryImpl implements IngredientRepository{

    private JdbcTemplate jdbc;

    @Autowired
    public IngredientRepositoryImpl ( JdbcTemplate jdbc ){
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Ingredient> getAll() {
        return jdbc.query(
                "select id, name, type from Ingredient",
                this::mapRowToIngredients);
    }

    @Override
    public Ingredient getById(String id) {
        return jdbc.queryForObject(
                "select id, name, type from Ingredient where id=?",
                this::mapRowToIngredients, id);
    }

    @Override
    public void add (Ingredient ingredient) {
        jdbc.update(
                "insert into Ingredient (id, name, type) value (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
    }

    private Ingredient mapRowToIngredients (ResultSet rs, int row) throws SQLException {
        return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")));
    }
}
