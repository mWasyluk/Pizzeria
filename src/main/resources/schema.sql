CREATE TABLE IF NOT EXISTS Ingredient(
    id VARCHAR (6) NOT NULL,
    name VARCHAR (50) NOT NULL,
    type VARCHAR (10) NOT NULL
);

CREATE TABLE IF NOT EXISTS Pizza_Ingredients(
    pizza_id BIGINT NOT NULL,
    ingredient_id VARCHAR (6) NOT NULL
);

CREATE TABLE IF NOT EXISTS Pizza(
    id IDENTITY NOT NULL,
    name VARCHAR (50) NOT NULL,
    date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Pizza_Order_Pizzas(
    order_id BIGINT NOT NULL,
    pizza_id BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS Pizza_Order(
    id IDENTITY NOT NULL,
    name VARCHAR (60) NOT NULL,
    street VARCHAR (60) NOT NULL,
    city VARCHAR (60) NOT NULL,
    zip_code VARCHAR (6) NOT NULL,
    cc_number VARCHAR (16) NOT NULL,
    cc_expiration VARCHAR (5) NOT NULL,
    cc_cvv VARCHAR (3) NOT NULL
);

ALTER TABLE Pizza_Ingredients 
    ADD FOREIGN KEY (pizza_id) REFERENCES Pizza (id);
ALTER TABLE Pizza_Ingredients 
    ADD FOREIGN KEY (ingredient_id) REFERENCES Ingredient (id);

ALTER TABLE Pizza_Order_Pizzas
    ADD FOREIGN KEY (order_id) REFERENCES Pizza_Order (id);
ALTER TABLE Pizza_Order_Pizzas
    ADD FOREIGN KEY (pizza_id) REFERENCES Pizza (id);