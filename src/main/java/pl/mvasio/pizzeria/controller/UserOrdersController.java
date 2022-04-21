package pl.mvasio.pizzeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.mvasio.pizzeria.data.IngredientRepository;
import pl.mvasio.pizzeria.data.OrderRepository;
import pl.mvasio.pizzeria.model.Ingredient;
import pl.mvasio.pizzeria.model.Order;
import pl.mvasio.pizzeria.model.Pizza;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class UserOrdersController {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private IngredientRepository ingredientRepo;


}
