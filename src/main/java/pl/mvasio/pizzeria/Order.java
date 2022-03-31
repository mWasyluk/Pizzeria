package pl.mvasio.pizzeria;

import lombok.Data;

@Data
public class Order {
    private String name;
    private String street;
    private String city;
    private String zipCode;
    private String ccNo;
    private String ccExpiration;
    private String ccCVV;
}
