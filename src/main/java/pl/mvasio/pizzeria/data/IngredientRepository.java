package pl.mvasio.pizzeria.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.mvasio.pizzeria.model.Ingredient;

@Repository
public interface IngredientRepository extends MongoRepository<Ingredient, String> {
}
