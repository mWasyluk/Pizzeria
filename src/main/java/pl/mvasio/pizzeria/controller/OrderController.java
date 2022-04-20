package pl.mvasio.pizzeria.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import pl.mvasio.pizzeria.model.Order;
import pl.mvasio.pizzeria.data.OrderRepository;
import pl.mvasio.pizzeria.data.UserRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepo;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public OrderController (OrderRepository orderRepo){
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String showOrderForm(Model model){
        return "orderForm.html";
    }

    @PostMapping
    public String processOrder (@Valid @ModelAttribute("order") Order order , Errors errors, SessionStatus sessionStatus, Authentication authentication){
        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors())
                log.info(e.toString());
            return "orderForm.html";
        }

        log.info("Supplied order: " + order);
        User user = (User) authentication.getPrincipal();
        order.setUsername(user.getUsername());
        orderRepo.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }

}
