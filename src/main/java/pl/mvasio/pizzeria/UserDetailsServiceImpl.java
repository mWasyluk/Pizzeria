package pl.mvasio.pizzeria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.mvasio.pizzeria.data.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepo;

    @Autowired
    public UserDetailsServiceImpl (UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails getUserDetails(String username) throws UsernameNotFoundException {
        User user = userRepo.getUser(username);

        if (user == null){
            throw new UsernameNotFoundException("Nie znaleziono użytkownika o takiej nazwie.");
        }
        return user;
    }
}
