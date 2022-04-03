package pl.mvasio.pizzeria.data;

import org.springframework.security.core.userdetails.UserDetails;
import pl.mvasio.pizzeria.User;

public interface UserRepository {
    void addUser(User user);
    User getUser(String username);
    User updateUser(String username);
    User removeUser(String username);
}
