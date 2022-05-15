package pl.mvasio.pizzeria.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.mvasio.pizzeria.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findAllByUsernameOrderByCreateDateDesc(String username);
    List<Order> findAllByUsernameOrderByCreateDateDesc(String username, Pageable pageable);
    long countByUsername(String username);
}
