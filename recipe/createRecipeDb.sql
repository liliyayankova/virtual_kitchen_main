CREATE DATABASE RecipeDb;

USE RecipeDb;

CREATE TABLE Recipe 
(
ID INT NOT NULL AUTO_INCREMENT,
TITLE VARCHAR(20),
CALORIES INT, 
PRIMARY KEY (ID)
);

CREATE TABLE Ingredient
(
ID INT NOT NULL AUTO_INCREMENT,
INGREDIENT_NAME VARCHAR(20),
PRIMARY KEY (ID)
);

CREATE TABLE Recipe_Ingredient
(
RECIPE_ID INT,
INGREDIENT_ID INT,
FOREIGN KEY (RECIPE_ID)
REFERENCES Recipe(id),
FOREIGN KEY (INGREDIENT_ID)
REFERENCES Ingredient(id)
);

insert into ingredient(ingredient_name) values("Avocado");
insert into ingredient(ingredient_name) values("Chicken");
insert into ingredient(ingredient_name) values("Brown Rice");
insert into ingredient(ingredient_name) values("Cauliflower");
insert into ingredient(ingredient_name) values("Red Pepper");
insert into ingredient(ingredient_name) values("Mature Cheddar");
insert into ingredient(ingredient_name) values("Wholemeal Bread");

insert into recipe(title,calories) values("Salad", 350);

insert into recipe(title,calories) values("Rice meal",500);

insert into recipe(title,calories) values("Chicken sandwhich",400);


insert into recipe_ingredient(recipe_id,ingredient_id) values(1,1);
insert into recipe_ingredient(recipe_id,ingredient_id) values(1,2);
insert into recipe_ingredient(recipe_id,ingredient_id) values(1,4);
insert into recipe_ingredient(recipe_id,ingredient_id) values(1,5);
insert into recipe_ingredient(recipe_id,ingredient_id) values(1,6);


insert into recipe_ingredient(recipe_id,ingredient_id) values(2,1);
insert into recipe_ingredient(recipe_id,ingredient_id) values(2,2);
insert into recipe_ingredient(recipe_id,ingredient_id) values(2,3);
insert into recipe_ingredient(recipe_id,ingredient_id) values(2,4);
insert into recipe_ingredient(recipe_id,ingredient_id) values(2,5);

insert into recipe_ingredient(recipe_id,ingredient_id) values(3,1);
insert into recipe_ingredient(recipe_id,ingredient_id) values(3,2);
insert into recipe_ingredient(recipe_id,ingredient_id) values(3,6);
insert into recipe_ingredient(recipe_id,ingredient_id) values(3,7);


drop table recipe_ingredient
drop table ingredient
drop table recipe

delete from recipe where id = 3 or id = 4 or id = 5;
select * from recipe;
delete from recipe_ingredient where recipe_id = 3 or recipe_id = 4 or recipe_id = 5;

select * from recipe_ingredient;