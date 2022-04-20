package pl.mvasio.pizzeria.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.mvasio.pizzeria.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}
