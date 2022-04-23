package pl.mvasio.pizzeria.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.mvasio.pizzeria.model.RegistrationForm;
import pl.mvasio.pizzeria.data.UserRepository;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/registration")
@SessionAttributes("registrationForm")
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
    public String processRegistration (@Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm,
                                       Errors errors){
        if (errors.hasErrors()) {
            return "registration.html";
        }
        userRepo.save(registrationForm.toUser(passwordEncoder));
        return "redirect:/";
    }
}
