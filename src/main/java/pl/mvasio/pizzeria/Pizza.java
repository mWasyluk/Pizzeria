package pl.mvasio.pizzeria;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Pizza {
    private Long id;
    private Date createDate;
    @Pattern(regexp = "^([a-zA-Z0-9_ ]{3,})$", message = "Nazwa powinna zawierać conajmniej trzy znaki.")
    private String name;
    @NotNull(message = "Wybierz rodzaj ciasta.")
    private String pie;
    @NotNull(message = "Wybierz rozmiar.")
    private String size;
    @NotNull(message = "Wybierz sos podstawowy.")
    private String sauce;
    @Size(min = 1, message = "Wybierz conajmniej jeden składnik dodatkowy.")
    private List<String> ingredients;
}
