DELETE FROM Ingredient;
DELETE FROM Pizza_Ingredients;
DELETE FROM Pizza;
DELETE FROM Ordered_Pizza;
DELETE FROM Pizza_Order;

INSERT INTO Ingredient (id, name, type)
    VALUES ('pie01', 'Tradycyjne', 'PIE');
INSERT INTO Ingredient (id, name, type)
    VALUES ('pie02', 'Grube', 'PIE');
INSERT INTO Ingredient (id, name, type)
    VALUES ('pie03', 'Serowe boki', 'PIE');

INSERT INTO Ingredient (id, name, type)
    VALUES ('siz01', 'Mała (24cm)', 'SIZE');
INSERT INTO Ingredient (id, name, type)
    VALUES ('siz02', 'Średnia (30cm)', 'SIZE');
INSERT INTO Ingredient (id, name, type)
    VALUES ('siz03', 'Duża (36cm)', 'SIZE');
INSERT INTO Ingredient (id, name, type)
    VALUES ('siz04', 'Tego nikt się nie spodziewał! (42cm)', 'SIZE');

INSERT INTO Ingredient (id, name, type)
    VALUES ('sau01', 'Pomidorowy', 'SAUCE');
INSERT INTO Ingredient (id, name, type)
    VALUES ('sau02', 'Czosnkowy', 'SAUCE');
INSERT INTO Ingredient (id, name, type)
    VALUES ('sau03', 'BBQ', 'SAUCE');

INSERT INTO Ingredient (id, name, type)
    VALUES ('che01', 'Cheedar', 'CHEESE');
INSERT INTO Ingredient (id, name, type)
    VALUES ('che02', 'Ementaler', 'CHEESE');

INSERT INTO Ingredient (id, name, type)
    VALUES ('mea01', 'Kurczak', 'MEAT');
INSERT INTO Ingredient (id, name, type)
    VALUES ('mea02', 'Kiełbasa', 'MEAT');
INSERT INTO Ingredient (id, name, type)
    VALUES ('mea03', 'Boczek', 'MEAT');
INSERT INTO Ingredient (id, name, type)
    VALUES ('mea04', 'Wołowina', 'MEAT');
INSERT INTO Ingredient (id, name, type)
    VALUES ('mea05', 'Salami', 'MEAT');
INSERT INTO Ingredient (id, name, type)
    VALUES ('mea06', 'Szyneczka', 'MEAT');

INSERT INTO Ingredient (id, name, type)
    VALUES ('veg01', 'Pieczarki', 'VEGGIE');
INSERT INTO Ingredient (id, name, type)
    VALUES ('veg02', 'Kukurydza', 'VEGGIE');
INSERT INTO Ingredient (id, name, type)
    VALUES ('veg03', 'Cebula', 'VEGGIE');
INSERT INTO Ingredient (id, name, type)
    VALUES ('veg04', 'Pikle', 'VEGGIE');
INSERT INTO Ingredient (id, name, type)
    VALUES ('veg05', 'Papryka', 'VEGGIE');
INSERT INTO Ingredient (id, name, type)
    VALUES ('veg06', 'Oliwka brazil', 'VEGGIE');
INSERT INTO Ingredient (id, name, type)
    VALUES ('veg07', 'Dżalapinto', 'VEGGIE');
INSERT INTO Ingredient (id, name, type)
    VALUES ('veg08', 'Rukola', 'VEGGIE');
