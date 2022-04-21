package pl.mvasio.pizzeria.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Pizzas")
public class Pizza {
    @Id
    private String id;
    private Date createDate;
    @Pattern(regexp = "^([a-zA-Z0-9_ ]{3,})$", message = "Nazwa powinna zawierać conajmniej trzy znaki.")
    private String pizzaName;
    @NotNull(message = "Wybierz rodzaj ciasta.")
    private String pie;
    @NotNull(message = "Wybierz rozmiar.")
    private String size;
    @NotNull(message = "Wybierz sos podstawowy.")
    private String sauce;
    @Size(min = 1, message = "Wybierz conajmniej jeden składnik dodatkowy.")
    private List<String> ingredients;
}
