package pl.mvasio.pizzeria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Arrays;
import java.util.Collection;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String fullName;
    @NonNull
    private String email;
    @NonNull
    private String phone;
    @NonNull
    private String street;
    @NonNull
    private String city;
    @NonNull
    private String zipCode;
    @NonNull
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
