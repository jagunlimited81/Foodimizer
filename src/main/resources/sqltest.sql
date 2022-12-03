drop table made_of;
drop table Ingredients;
drop table Recipes;
drop table STOCK;


create TABLE Ingredients (
  ingredientID int NOT NULL,
  name VARCHAR(255),
  PRIMARY KEY (ingredientID)
);

create TABLE Recipes (
  recipeID int NOT NULL,
  name VARCHAR(255),
  PRIMARY KEY (recipeID)
);

create TABLE STOCK (
ingredientID int NOT NULL

  );

create TABLE made_of(
  recipeID INT REFERENCES Recipes(recipeID),
  ingredientID INT REFERENCES Ingredients(ingredientID)
);

insert into Ingredients (ingredientID, name)
VALUES (1, 'ham');
insert into Ingredients (ingredientID, name)
VALUES (2, 'egg');
insert into Ingredients (ingredientID, name)
VALUES (3, 'cheese');
insert into Ingredients (ingredientID, name)
VALUES (4, 'bacon');
insert into Ingredients (ingredientID, name)
VALUES (5, 'bread');
insert into Ingredients (ingredientID, name)
VALUES (6, 'salt');
insert into Ingredients (ingredientID, name)
VALUES (7, 'pepper');
insert into Ingredients (ingredientID, name)
VALUES (8, 'peanut butter');
insert into Ingredients (ingredientID, name)
VALUES (9, 'jelly');

insert into Recipes (recipeID, name)
VALUES (1, 'toast');
insert into Recipes (recipeID, name)
VALUES (2, 'english muffin');
insert into Recipes (recipeID, name)
VALUES (3, 'egg sandwich');
insert into Recipes (recipeID, name)
VALUES (4, 'PB and J');

insert into made_of (recipeID, ingredientID)
VALUES (1, 5);

insert into made_of (recipeID, ingredientID)
VALUES (2, 2);
insert into made_of (recipeID, ingredientID)
VALUES (2, 5);
insert into made_of (recipeID, ingredientID)
VALUES (2, 1);
insert into made_of (recipeID, ingredientID)
VALUES (2, 3);

insert into made_of (recipeID, ingredientID)
VALUES (3, 2);
insert into made_of (recipeID, ingredientID)
VALUES (3, 5);

insert into made_of (recipeID, ingredientID)
VALUES (4, 5);
insert into made_of (recipeID, ingredientID)
VALUES (4, 8);
insert into made_of (recipeID, ingredientID)
VALUES (4, 9);

insert into STOCK (ingredientID)
VALUES (5);
insert into STOCK (ingredientID)
VALUES (8);
insert into STOCK (ingredientID)
VALUES (9);

-- Select recipe name from Recipes if the ingredients used to make the recipe are
-- a subset of pantry ingredients in james's profile (id 1)
-- output should be toast, egg sandwich
select m.recipeID
from made_of m
left join STOCK i on m.ingredientID = i.ingredientID
group by m.recipeId
having count(*) = count(i.ingredientID)