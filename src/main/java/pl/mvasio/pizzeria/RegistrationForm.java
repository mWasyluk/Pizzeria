package pl.mvasio.pizzeria;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class RegistrationForm {

    @Pattern(regexp = "^.{5,}$", message = "Nazwa użytkownika musi zawierać co najmniej 5 znaków.")
    @Pattern(regexp = "^.{0,30}$", message = "Nazwa użytkownika może zawierać maksymalnie 30 znaków.")
    private String username;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Hasło musi zawierać co najmniej 8 znaków (min. 1 litera i 1 cyfra).")
    private String password;
    private String confirmPassword;
    @Pattern(regexp = "^[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]{2,}( [A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]{2,})+$", message = "Niepoprawny format. Imiona/nazwiska powinny być oddzielone spacją i zawierać co najmniej 2 znaki.")
    private String fullName;
    @NotBlank(message = "E-mail musi mieć format '''moj@email.pl''.")
    @Email(message = "E-mail musi mieć format ''moj@email.pl''.")
    private String email;
    @Pattern(regexp = "^[0-9]{9}$", message = "Numer musi zawierać 9 cyfr (bez dodatkowych znaków).")
    private String phone;
    @Pattern(regexp = "^(.{3,} [\\d\\w\\/-]+)$", message = "To pole musi zawierać ulice i numer domu (również mieszkania, jeżeli występuje) oddzielone spacją.")
    private String street;
    @Pattern(regexp = "^([\\w]{3,} ?[\\w]?)$", message = "Nazwa miasta musi zawierać co najmniej 3 litery.")
    private String city;
    @Pattern(regexp = "^(([0-9]{2})-([0-9]{3}))$", message = "Kod pocztowy musi mieć format ''00-000''.")
    private String zipCode;

    public User toUser (PasswordEncoder passwordEncoder){
        return new User(username, passwordEncoder.encode(password), fullName, email, phone, street, city, zipCode, true);
    }

    public boolean isPasswordConfirmed (){
        return password.equals(confirmPassword);
    }
}
