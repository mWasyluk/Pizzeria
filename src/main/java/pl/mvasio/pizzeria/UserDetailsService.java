package pl.mvasio.pizzeria;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
    UserDetails getUserDetails (String username) throws UsernameNotFoundException;
}
