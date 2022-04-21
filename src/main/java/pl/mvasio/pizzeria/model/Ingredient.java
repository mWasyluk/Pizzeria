package pl.mvasio.pizzeria.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@Document(collection = "Ingredients")
public class Ingredient {

    @NonNull
    private String id;
    @NonNull
    private String name;
    @NonNull
    private Type type;

    public enum Type {
        PIE, SIZE, SAUCE, CHEESE, MEAT, VEGGIE
    }

    public static boolean isSauce(Ingredient ingredient){
        return ingredient.getType().equals(Type.SAUCE);
    }
}
