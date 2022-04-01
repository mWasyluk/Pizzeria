package pl.mvasio.pizzeria;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.mvasio.pizzeria.data.IngredientRepository;

import javax.validation.Valid;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/design")
public class DesignPageController {
    private IngredientRepository repo;

    @Autowired
    public DesignPageController (IngredientRepository repo){
        this.repo = repo;
    }

    @ModelAttribute
    public void setModel (Model model) {
        List <Ingredient> ingredients = new LinkedList<>();
        repo.getAll().forEach(ingredients::add);

        Ingredient.Type [] types = Ingredient.Type.values();
        model.addAttribute("types", types);
        for (Ingredient.Type t: types) {
            model.addAttribute(t.toString(), filterByType(ingredients, t));
        }
    }

    @GetMapping
    public String showDesignOption (Model model){
        model.addAttribute("design", new Pizza());
        return "design.html";
    }

    @PostMapping
    public String processDesign (@Valid @ModelAttribute("design") Pizza design, Errors errors, Model model){
        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors())
                log.info(e.toString());
            return "design.html";
        }

        log.info("Supplied design: " + design);
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
