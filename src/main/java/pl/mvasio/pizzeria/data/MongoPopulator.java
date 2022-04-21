package pl.mvasio.pizzeria.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.mvasio.pizzeria.model.Ingredient;

import java.util.Arrays;
import java.util.List;

@Component
public class MongoPopulator implements CommandLineRunner {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public MongoPopulator(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Ingredient> ingredientList = Arrays.asList(
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

        ingredientRepository.deleteAll();

        ingredientRepository.saveAll(ingredientList);
    }
}