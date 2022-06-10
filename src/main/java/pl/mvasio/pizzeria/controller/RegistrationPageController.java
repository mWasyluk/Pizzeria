package pl.mvasio.pizzeria.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mvasio.pizzeria.data.UserRepository;
import pl.mvasio.pizzeria.model.RegistrationForm;
import pl.mvasio.pizzeria.model.User;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/registration")
public class RegistrationPageController {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationPageController (UserRepository userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute ("registrationForm")
    protected RegistrationForm registrationForm(){
        return new RegistrationForm();
    }

    @GetMapping
    public String showRegistrationForm(){
        return "registration.html";
    }

    @PostMapping
    public String processRegistration (@Valid RegistrationForm registrationForm,
                                       Errors errors, Model model){
        User byUsername = userRepo.findByUsername(registrationForm.getUsername());
        if (byUsername != null) {
            model.addAttribute("usernameInUse", true);
            return "registration.html";
        }
        if (errors.hasErrors()) {
            return "registration.html";
        }
        userRepo.save(registrationForm.toUser(passwordEncoder));
        return "redirect:/";
    }
}
