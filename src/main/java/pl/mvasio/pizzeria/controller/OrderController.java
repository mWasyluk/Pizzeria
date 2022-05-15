package pl.mvasio.pizzeria.controller;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import pl.mvasio.pizzeria.data.IngredientRepository;
import pl.mvasio.pizzeria.data.OrderRepository;
import pl.mvasio.pizzeria.model.Order;
import pl.mvasio.pizzeria.model.User;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@ConfigurationProperties (prefix = "gmpizza.orders")
public class OrderController {

    private final OrderRepository orderRepo;
    private final IngredientRepository ingredientRepo;
    @Setter
    private int pageSize = 5;

    @Autowired
    public OrderController (OrderRepository orderRepo, IngredientRepository ingredientRepo){
        this.orderRepo = orderRepo;
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute("currentUsername")
    public String currentUserName(Authentication authentication) {
        return authentication.getName();
    }

    @GetMapping("/form")
    public String showOrderForm(Model model){
        return "orderForm.html";
    }

    @PostMapping("/form")
    public String processOrder (@Valid @ModelAttribute("order") Order order , Errors errors, SessionStatus sessionStatus, Authentication authentication){
        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors())
                log.info(e.toString());
            return "orderForm.html";
        }

        log.info("Supplied order: " + order);
        User user = (User) authentication.getPrincipal();
        order.setUsername(user.getUsername());
        order.setCreateDate(new Date());
        orderRepo.save(order);
        sessionStatus.setComplete();
        return "redirect:/orders";
    }

    @ModelAttribute (name = "ingredientRepo")
    public IngredientRepository ingredientRepo(){
        return ingredientRepo;
    }

    @GetMapping("/current")
    public String getCurrentOrderDetails(Model model){
        if (model.getAttribute("order") == null)
            return "redirect:/design";
        return "currentOrder.html";
    }

    @GetMapping ("/current/reset")
    public String deletePizzasFromCurrentOrder(Model model){
        model.addAttribute("order", new Order());
        return "currentOrder.html";
    }

    @GetMapping()
    public String getUserOrders(@ModelAttribute("currentUsername")  String username,
                                @RequestParam(required = false, name = "page", defaultValue = "1") long page,
                                Model model){
        long ordersQuantity = orderRepo.countByUsername(username);
        long maxPageNumber = ordersQuantity % pageSize == 0 && ordersQuantity > 0 ? ordersQuantity / pageSize : ordersQuantity / pageSize + 1;
        model.addAttribute("maxPageNumber", maxPageNumber);

        long fixedPage = page > maxPageNumber ? maxPageNumber : page;
        Pageable pageable = PageRequest.of((int) (fixedPage - 1), pageSize);
        model.addAttribute("pageNumber", fixedPage);

        List<Order> orders = orderRepo.findAllByUsernameOrderByCreateDateDesc(username, pageable);
        model.addAttribute("pagedOrders", orders);

        return "userOrders.html";
    }
}
