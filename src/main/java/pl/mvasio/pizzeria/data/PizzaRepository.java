package pl.mvasio.pizzeria.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.mvasio.pizzeria.model.Pizza;

@Repository
public interface PizzaRepository extends MongoRepository<Pizza, String> {
}
