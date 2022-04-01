package pl.mvasio.pizzeria.data;

import pl.mvasio.pizzeria.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> getAll();
    Ingredient getById(String id);
    void add(Ingredient ingredient);
}
