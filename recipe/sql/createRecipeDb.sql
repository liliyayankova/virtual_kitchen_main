USE RecipeDb;
go

CREATE TABLE recipe
(
ID INT NOT NULL AUTO_INCREMENT,
TITLE VARCHAR(20), 
PRIMARY KEY (ID)
);
go

CREATE TABLE ingredient
(
ID INT NOT NULL AUTO_INCREMENT,
INGREDIENT_NAME VARCHAR(20),
BRAND VARCHAR(20),
CALORIES INT,
PRIMARY KEY (ID)
);
go

CREATE TABLE recipe_ingredient
(
RECIPE_ID INT,
INGREDIENT_ID INT,
FOREIGN KEY (RECIPE_ID)
REFERENCES recipe(id),
FOREIGN KEY (INGREDIENT_ID)
REFERENCES ingredient(id)
);
go

INSERT INTO ingredient VALUES (null, 'tomatoes', 'Tesco', 10);
go
INSERT INTO ingredient VALUES (null, 'cucumber', 'Tesco', 15);
go
INSERT INTO ingredient VALUES (null, 'onion', 'Tesco', 16);
go
INSERT INTO ingredient VALUES (null, 'cheese', 'Tesco', 17);
go
INSERT INTO ingredient VALUES (null, 'olives', 'Tesco', 20);
go
INSERT INTO recipe VALUES (null, 'salad');
go
INSERT INTO ingredient VALUES (null, 'carrots', 'Tesco', 10);
go
INSERT INTO ingredient VALUES (null, 'tomatoes', 'Tesco', 10);
go
INSERT INTO ingredient VALUES (null, 'potatoes', 'Tesco', 150);
go
INSERT INTO ingredient VALUES (null, 'celery', 'Tesco', 100);
go
INSERT INTO ingredient VALUES (null, 'green beans', 'Tesco', 125);
go
INSERT INTO ingredient VALUES (null, 'corn', 'Tesco', 130);
go
INSERT INTO ingredient VALUES (null, 'peas', 'Tesco', 110);
go
INSERT INTO ingredient VALUES (null, 'garlic', 'Tesco', 50);
go
INSERT INTO recipe VALUES (null, 'soup');
go
INSERT INTO ingredient VALUES (null, 'flour', 'Tesco', 250);
go
INSERT INTO ingredient VALUES (null, 'milk', 'Tesco', 100);
go
INSERT INTO ingredient VALUES (null, 'sugar', 'Tesco', 25);
go
INSERT INTO ingredient VALUES (null, 'baking powder', 'Tesco', 10);
go
INSERT INTO ingredient VALUES (null, 'eggs', 'Tesco', 150);
go
INSERT INTO recipe VALUES (null, 'pancakes');
go
INSERT INTO recipe_ingredient VALUES (1,1);
go
INSERT INTO recipe_ingredient VALUES (1,2);
go
INSERT INTO recipe_ingredient VALUES (1,3);
go
INSERT INTO recipe_ingredient VALUES (1,4);
go
INSERT INTO recipe_ingredient VALUES (1,5);
go
INSERT INTO recipe_ingredient VALUES (2,6);
go
INSERT INTO recipe_ingredient VALUES (2,7);
go
INSERT INTO recipe_ingredient VALUES (2,8);
go
INSERT INTO recipe_ingredient VALUES (2,9);
go
INSERT INTO recipe_ingredient VALUES (3,14);
go
INSERT INTO recipe_ingredient VALUES (3,15);
go
INSERT INTO recipe_ingredient VALUES (3,16);
go