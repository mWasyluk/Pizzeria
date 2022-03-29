package pl.mvasio.pizzeria;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
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
}
