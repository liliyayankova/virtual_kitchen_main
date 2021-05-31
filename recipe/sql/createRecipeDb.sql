USE RecipeDb;
go

CREATE TABLE recipe
(
ID INT NOT NULL AUTO_INCREMENT,
TITLE VARCHAR(20),
CALORIES INT,
PRIMARY KEY (ID)
);
go

CREATE TABLE ingredient
(
ID INT NOT NULL AUTO_INCREMENT,
INGREDIENT_NAME VARCHAR(20),
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

insert into ingredient(ingredient_name) values("Avocado");
go
insert into ingredient(ingredient_name) values("Chicken");
go
insert into ingredient(ingredient_name) values("Brown Rice");
go
insert into ingredient(ingredient_name) values("Cauliflower");
go
insert into ingredient(ingredient_name) values("Red Pepper");
go
insert into ingredient(ingredient_name) values("Mature Cheddar");
go
insert into ingredient(ingredient_name) values("Wholemeal Bread");
go

insert into recipe(title,calories) values("Salad", 350);
go
insert into recipe(title,calories) values("Rice meal",500);
go
insert into recipe(title,calories) values("Chicken sandwhich",400);
go

insert into recipe_ingredient(recipe_id,ingredient_id) values(1,1);
go
insert into recipe_ingredient(recipe_id,ingredient_id) values(1,2);
go
insert into recipe_ingredient(recipe_id,ingredient_id) values(1,4);
go
insert into recipe_ingredient(recipe_id,ingredient_id) values(1,5);
go
insert into recipe_ingredient(recipe_id,ingredient_id) values(1,6);
go

insert into recipe_ingredient(recipe_id,ingredient_id) values(2,1);
go
insert into recipe_ingredient(recipe_id,ingredient_id) values(2,2);
go
insert into recipe_ingredient(recipe_id,ingredient_id) values(2,3);
go
insert into recipe_ingredient(recipe_id,ingredient_id) values(2,4);
go
insert into recipe_ingredient(recipe_id,ingredient_id) values(2,5);
go

insert into recipe_ingredient(recipe_id,ingredient_id) values(3,1);
go
insert into recipe_ingredient(recipe_id,ingredient_id) values(3,2);
go
insert into recipe_ingredient(recipe_id,ingredient_id) values(3,6);
go
insert into recipe_ingredient(recipe_id,ingredient_id) values(3,7);
go
