package pl.mvasio.pizzeria.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.mvasio.pizzeria.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String userName);
}
