CREATE SCHEMA IF NOT EXISTS cookbook
    AUTHORIZATION "user";

CREATE TABLE IF NOT EXISTS cookbook.ingredient
(
    id bigint generated by default as identity,
    category integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT ingredient_pkey PRIMARY KEY (id),
    CONSTRAINT uk_name UNIQUE (name)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS cookbook.ingredient
    OWNER to "user";

CREATE TABLE IF NOT EXISTS cookbook.recipe
(
    id bigint generated by default as identity,
    instructions character varying(255) COLLATE pg_catalog."default" NOT NULL,
    servings integer NOT NULL,
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT recipe_pkey PRIMARY KEY (id),
    CONSTRAINT uk_title UNIQUE (title)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS cookbook.recipe
    OWNER to "user";

CREATE TABLE IF NOT EXISTS cookbook.recipe_ingredients
(
    recipe_id bigint NOT NULL,
    ingredients_id bigint NOT NULL,
    CONSTRAINT fk_ingredients_id FOREIGN KEY (ingredients_id)
        REFERENCES cookbook.ingredient (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id)
        REFERENCES cookbook.recipe (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS cookbook.recipe_ingredients
    OWNER to "user";

-- Recipe 1: Fried Egg Taco
insert into recipe (id, instructions, servings, title) values (1, 'Cook the tortilla. Cooking the eggs. Finishing the tacos', 1, 'Fried Egg Taco');
insert into ingredient (id, category, name) values (1, 1, 'Egg');
insert into ingredient (id, category, name) values (2, 0, 'Taco');
insert into recipe_ingredients (recipe_id, ingredients_id) values (1, 1);
insert into recipe_ingredients (recipe_id, ingredients_id) values (1, 2);

-- Recipe 2: Instant Pot Rice
insert into recipe (id, instructions, servings, title) values (2, 'Rinse the rice. Add the rice and water to the cooker. Cook', 1, 'Instant Pot Rice');
insert into ingredient (id, category, name) values (3, 0, 'Rice');
insert into ingredient (id, category, name) values (4, 0, 'Salt');
insert into ingredient (id, category, name) values (5, 0, 'Water');
insert into recipe_ingredients (recipe_id, ingredients_id) values (2, 3);
insert into recipe_ingredients (recipe_id, ingredients_id) values (2, 4);
insert into recipe_ingredients (recipe_id, ingredients_id) values (2, 5);

-- Recipe 3: Sweet Potato Fries
insert into recipe (id, instructions, servings, title) values (3, 'Preheat oven and roasting pan. Peel and cut sweet potatoes into wedges. Toss with oil, salt, spices. Spread out onto preheated pan. Bake', 1, 'Sweet Potato Fries');
insert into ingredient (id, category, name) values (6, 0, 'Sweet potatoes');
insert into ingredient (id, category, name) values (7, 0, 'Extra virgin olive oil');
insert into ingredient (id, category, name) values (8, 0, 'Kosher salt');
insert into ingredient (id, category, name) values (9, 0, 'Chipotle powder');
insert into recipe_ingredients (recipe_id, ingredients_id) values (3, 6);
insert into recipe_ingredients (recipe_id, ingredients_id) values (3, 7);
insert into recipe_ingredients (recipe_id, ingredients_id) values (3, 8);
insert into recipe_ingredients (recipe_id, ingredients_id) values (3, 9);

-- Recipe 4: Spaghetti Carbonara
insert into recipe (id, instructions, servings, title) values (4, 'Cook spaghetti. Cook pancetta. Mix eggs and cheese. Combine all with pasta', 2, 'Spaghetti Carbonara');
insert into ingredient (id, category, name) values (10, 0, 'Spaghetti');
insert into ingredient (id, category, name) values (11, 1, 'Pancetta');
insert into ingredient (id, category, name) values (13, 0, 'Parmesan cheese');
insert into ingredient (id, category, name) values (14, 0, 'Black pepper');
insert into recipe_ingredients (recipe_id, ingredients_id) values (4, 10);
insert into recipe_ingredients (recipe_id, ingredients_id) values (4, 11);
insert into recipe_ingredients (recipe_id, ingredients_id) values (4, 1);
insert into recipe_ingredients (recipe_id, ingredients_id) values (4, 13);
insert into recipe_ingredients (recipe_id, ingredients_id) values (4, 14);

-- Recipe 5: Guacamole
insert into recipe (id, instructions, servings, title) values (5, 'Mash avocados. Mix in lime juice, salt, and other ingredients. Serve', 4, 'Guacamole');
insert into ingredient (id, category, name) values (15, 0, 'Avocado');
insert into ingredient (id, category, name) values (16, 0, 'Lime juice');
insert into ingredient (id, category, name) values (18, 0, 'Cilantro');
insert into ingredient (id, category, name) values (19, 0, 'Diced onion');
insert into ingredient (id, category, name) values (20, 0, 'Tomato');
insert into ingredient (id, category, name) values (21, 0, 'Garlic');
insert into ingredient (id, category, name) values (22, 0, 'Jalapeño');
insert into recipe_ingredients (recipe_id, ingredients_id) values (5, 15);
insert into recipe_ingredients (recipe_id, ingredients_id) values (5, 16);
insert into recipe_ingredients (recipe_id, ingredients_id) values (5, 8);
insert into recipe_ingredients (recipe_id, ingredients_id) values (5, 18);
insert into recipe_ingredients (recipe_id, ingredients_id) values (5, 19);
insert into recipe_ingredients (recipe_id, ingredients_id) values (5, 20);
insert into recipe_ingredients (recipe_id, ingredients_id) values (5, 21);
insert into recipe_ingredients (recipe_id, ingredients_id) values (5, 22);

-- Recipe 6: Chicken Stir Fry
insert into recipe (id, instructions, servings, title) values (6, 'Cook chicken. Stir-fry vegetables. Mix sauce ingredients. Combine all and stir-fry', 3, 'Chicken Stir Fry');
insert into ingredient (id, category, name) values (23, 1, 'Chicken breast');
insert into ingredient (id, category, name) values (24, 0, 'Bell peppers');
insert into ingredient (id, category, name) values (25, 0, 'Broccoli');
insert into ingredient (id, category, name) values (26, 0, 'Soy sauce');
insert into ingredient (id, category, name) values (28, 0, 'Ginger');
insert into ingredient (id, category, name) values (29, 0, 'Olive oil');
insert into recipe_ingredients (recipe_id, ingredients_id) values (6, 23);
insert into recipe_ingredients (recipe_id, ingredients_id) values (6, 24);
insert into recipe_ingredients (recipe_id, ingredients_id) values (6, 25);
insert into recipe_ingredients (recipe_id, ingredients_id) values (6, 26);
insert into recipe_ingredients (recipe_id, ingredients_id) values (6, 21);
insert into recipe_ingredients (recipe_id, ingredients_id) values (6, 28);
insert into recipe_ingredients (recipe_id, ingredients_id) values (6, 29);

-- Recipe 7: Beef Tacos
insert into recipe (id, instructions, servings, title) values (7, 'Cook the beef. Prepare the toppings. Assemble the tacos', 4, 'Beef Tacos');
insert into ingredient (id, category, name) values (30, 1, 'Ground beef');
insert into ingredient (id, category, name) values (31, 0, 'Taco shells');
insert into ingredient (id, category, name) values (32, 0, 'Lettuce');
insert into ingredient (id, category, name) values (34, 0, 'Cheddar cheese');
insert into ingredient (id, category, name) values (35, 0, 'Sour cream');
insert into recipe_ingredients (recipe_id, ingredients_id) values (7, 30);
insert into recipe_ingredients (recipe_id, ingredients_id) values (7, 31);
insert into recipe_ingredients (recipe_id, ingredients_id) values (7, 32);
insert into recipe_ingredients (recipe_id, ingredients_id) values (7, 20);
insert into recipe_ingredients (recipe_id, ingredients_id) values (7, 34);
insert into recipe_ingredients (recipe_id, ingredients_id) values (7, 35);

-- Recipe 8: Caesar Salad
insert into recipe (id, instructions, servings, title) values (8, 'Prepare the dressing. Toss the lettuce with dressing, croutons, and cheese', 2, 'Caesar Salad');
insert into ingredient (id, category, name) values (36, 0, 'Romaine lettuce');
insert into ingredient (id, category, name) values (37, 0, 'Caesar dressing');
insert into ingredient (id, category, name) values (38, 0, 'Croutons');
insert into recipe_ingredients (recipe_id, ingredients_id) values (8, 36);
insert into recipe_ingredients (recipe_id, ingredients_id) values (8, 37);
insert into recipe_ingredients (recipe_id, ingredients_id) values (8, 38);
insert into recipe_ingredients (recipe_id, ingredients_id) values (8, 13);

-- Recipe 9: Banana Smoothie
insert into recipe (id, instructions, servings, title) values (9, 'Combine all recipe_ingredients in a blender. Blend until smooth', 1, 'Banana Smoothie');
insert into ingredient (id, category, name) values (40, 0, 'Banana');
insert into ingredient (id, category, name) values (41, 0, 'Milk');
insert into ingredient (id, category, name) values (42, 0, 'Honey');
insert into ingredient (id, category, name) values (43, 0, 'Ice cubes');
insert into recipe_ingredients (recipe_id, ingredients_id) values (9, 40);
insert into recipe_ingredients (recipe_id, ingredients_id) values (9, 41);
insert into recipe_ingredients (recipe_id, ingredients_id) values (9, 42);
insert into recipe_ingredients (recipe_id, ingredients_id) values (9, 43);
