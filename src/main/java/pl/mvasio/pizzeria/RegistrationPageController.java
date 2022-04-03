package pl.mvasio.pizzeria;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import pl.mvasio.pizzeria.data.OrderRepository;
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
    public String processRegistration (@Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm , Errors errors){
        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors())
                log.info(e.toString());
            return "registration.html";
        }

        log.info("Supplied account: " + registrationForm);
        userRepo.addUser(registrationForm.toUser(passwordEncoder));
        return "redirect:/";
    }
}
