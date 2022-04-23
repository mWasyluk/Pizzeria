package pl.mvasio.pizzeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.mvasio.pizzeria.data.UserRepository;
import pl.mvasio.pizzeria.model.RegistrationForm;
import pl.mvasio.pizzeria.model.User;

import javax.validation.Valid;
import java.net.http.HttpResponse;

@Controller
@RequestMapping("/account")
public class AccountController {

    private UserRepository userRepository;

    @Autowired
    public AccountController (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @ModelAttribute ("user")
    public User getAuthenticatedUser(Authentication authentication){
        return userRepository.findByUsername(authentication.getName());
    }

    @GetMapping
    public String getAccount(){
        return "account.html";
    }

    @GetMapping("/settings")
    public String getAccountSettings(Model model, @ModelAttribute("user") User user){
        var form = new RegistrationForm();
        form.setUsername(user.getUsername());
        form.setFullName(user.getFullName());
        form.setEmail(user.getEmail());
        form.setPhone(user.getPhone());
        form.setStreet(user.getStreet());
        form.setCity(user.getCity());
        form.setZipCode(user.getZipCode());
        model.addAttribute("newUserData", form);

        return "account-settings.html";
    }

    @PostMapping("/settings")
    public String changeUserData( @ModelAttribute("user") User user, @Valid @ModelAttribute("newUserData") RegistrationForm registrationForm,
                                 Errors errors){
        if (errors.hasErrors()) {
            return "account-settings.html";
        }
        user.setFullName(registrationForm.getFullName());
        user.setEmail(registrationForm.getEmail());
        user.setPhone(registrationForm.getPhone());
        user.setStreet(registrationForm.getStreet());
        user.setCity(registrationForm.getCity());
        user.setZipCode(registrationForm.getZipCode());
        userRepository.save(user);
        return "redirect:/account";
    }
}
