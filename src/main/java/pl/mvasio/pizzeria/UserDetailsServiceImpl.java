package pl.mvasio.pizzeria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.mvasio.pizzeria.data.UserRepository;
import pl.mvasio.pizzeria.model.User;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepo;

    @Autowired
    public UserDetailsServiceImpl (UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails getUserDetails(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("Nie znaleziono użytkownika o takiej nazwie.");
        }
        return user;
    }
}
