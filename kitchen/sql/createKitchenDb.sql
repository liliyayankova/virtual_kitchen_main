use KitchenDb;
go

CREATE TABLE kitchen
(
ID INT NOT NULL AUTO_INCREMENT,
KITCHEN_NAME VARCHAR(20),
FIRST_ADDRESS VARCHAR(20),
TOWN VARCHAR(20),
EMAIL VARCHAR(50), 
PRIMARY KEY (ID),
UNIQUE KEY (EMAIL)
);
go

CREATE TABLE kitchen_recipe
(
ID INT NOT NULL AUTO_INCREMENT,
KITCHEN_ID int,
RECIPE_ID int,
QUANTITY int,
FOREIGN KEY (KITCHEN_ID)
REFERENCES kitchen(ID),
PRIMARY KEY (ID)
);
go

insert into kitchen(kitchen_name,first_address,town,email) values ("Kobi's Kitchen","UoS","Guildford","KobisKitchen@gmail.com");
go
insert into kitchen(kitchen_name,first_address,town,email) values ("Tobi's Kitchen","UoS","Guildford","TobisKitchen@gmail.com");
go

insert into kitchen_recipe(kitchen_id,recipe_id,quantity) values(1,1,10);
go
insert into kitchen_recipe(kitchen_id,recipe_id,quantity) values(1,2,2);
go
insert into kitchen_recipe(kitchen_id,recipe_id,quantity) values(2,1,3);
go
insert into kitchen_recipe(kitchen_id,recipe_id,quantity) values(2,2,4);
go
insert into kitchen_recipe(kitchen_id,recipe_id,quantity) values(1,3,1);
go