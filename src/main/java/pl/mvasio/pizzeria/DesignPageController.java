package pl.mvasio.pizzeria;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/design")
public class DesignPageController {

    @GetMapping
    public String showDesignOption(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("pie01", "Tradycyjne", Ingredient.Type.PIE),
                new Ingredient("pie02", "Grube", Ingredient.Type.PIE),
                new Ingredient("pie03", "Serowe brzegi", Ingredient.Type.PIE),
                new Ingredient("siz01", "Mała (24cm)", Ingredient.Type.SIZE),
                new Ingredient("siz02", "Średnia (30cm)", Ingredient.Type.SIZE),
                new Ingredient("siz03", "Duża (42cm)", Ingredient.Type.SIZE),
                new Ingredient("sau01", "Pomidorowy", Ingredient.Type.SAUCE),
                new Ingredient("sau02", "Czosnkowy", Ingredient.Type.SAUCE),
                new Ingredient("sau03", "BBQ", Ingredient.Type.SAUCE),
                new Ingredient("che01", "Cheedar", Ingredient.Type.CHEESE),
                new Ingredient("che02", "Ementaler", Ingredient.Type.CHEESE),
                new Ingredient("mea01", "Kurczak", Ingredient.Type.MEAT),
                new Ingredient("mea02", "Kiełbasa", Ingredient.Type.MEAT),
                new Ingredient("mea03", "Boczek", Ingredient.Type.MEAT),
                new Ingredient("mea04", "Wołowina", Ingredient.Type.MEAT),
                new Ingredient("mea05", "Salami", Ingredient.Type.MEAT),
                new Ingredient("mea06", "Szyneczka", Ingredient.Type.MEAT),
                new Ingredient("veg01", "Pieczarki", Ingredient.Type.VEGGIE),
                new Ingredient("veg02", "Kukurydza", Ingredient.Type.VEGGIE),
                new Ingredient("veg03", "Cebula", Ingredient.Type.VEGGIE),
                new Ingredient("veg04", "Pikle", Ingredient.Type.VEGGIE),
                new Ingredient("veg05", "Papryka", Ingredient.Type.VEGGIE),
                new Ingredient("veg06", "Oliwka brazil", Ingredient.Type.VEGGIE),
                new Ingredient("veg07", "Dżalapinto", Ingredient.Type.VEGGIE),
                new Ingredient("veg08", "Rukola", Ingredient.Type.VEGGIE)
        );

        Ingredient.Type [] types = Ingredient.Type.values();
        model.addAttribute("types", types);
        for (Ingredient.Type t: types) {
            model.addAttribute(t.toString(), filterByType(ingredients, t));
        }

        return "design.html";
    }

    @PostMapping
    public String processDesign ( Pizza design ){
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
