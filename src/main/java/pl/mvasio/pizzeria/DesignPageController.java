package pl.mvasio.pizzeria;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.mvasio.pizzeria.data.IngredientRepository;
import pl.mvasio.pizzeria.data.PizzaRepository;

import javax.validation.Valid;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignPageController {
    private IngredientRepository ingredientRepo;
    private PizzaRepository pizzaRepo;

    @Autowired
    public DesignPageController (IngredientRepository ingredientRepo, PizzaRepository pizzaRepo){
        this.ingredientRepo = ingredientRepo;
        this.pizzaRepo = pizzaRepo;
    }

    @ModelAttribute
    public void setModel (Model model) {
        List <Ingredient> ingredients = new LinkedList<>();
        ingredientRepo.getAll().forEach(ingredients::add);

        Ingredient.Type [] types = Ingredient.Type.values();
        model.addAttribute("types", types);
        for (Ingredient.Type t: types) {
            model.addAttribute(t.toString(), filterByType(ingredients, t));
        }
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "design")
    public Pizza taco() {
        return new Pizza();
    }

    @GetMapping
    public String showDesignOption (Model model){
        return "design.html";
    }

    @PostMapping
    public String processDesign (@Valid @ModelAttribute("design") Pizza design, Errors errors, @ModelAttribute("order") Order order){
        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors())
                log.info(e.toString());
            return "design.html";
        }

        log.info("Supplied design: " + design);
        pizzaRepo.add(design);
        order.addPizza(design);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(
            Collection<? extends Ingredient> ingredients,
            Ingredient.Type t) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(t))
                .collect(Collectors.toList());
    }


}
