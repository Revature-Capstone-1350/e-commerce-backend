alter table if exists skyview.user_role
add column if not exists role_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT null,
add column if not exists "name" VARCHAR(50) NOT null;

alter table if exists skyview.order_status
add column if not exists order_status_id int generated always as identity primary key,
add column if not exists "name" varchar(50) not null;

alter table if exists skyview.address
add column if not exists address_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
add column if not exists street VARCHAR NOT NULL,
add column if not exists street2 VARCHAR,
add column if not exists state VARCHAR(2) NOT NULL,
alter column state type VARCHAR(2),
add column if not exists city VARCHAR(50) NOT NULL,
add column if not exists postal_code VARCHAR(10) NOT null;

alter table if exists skyview.category
add column if not exists category_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
add column if not exists "name" VARCHAR(50) NOT null;

alter table if exists skyview."user"
add column if not exists user_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
add column if not exists first_name VARCHAR(50) NOT NULL,
add column if not exists last_name VARCHAR(50),
add column if not exists email VARCHAR(255) NOT NULL UNIQUE,
add column if not exists "password" VARCHAR(64) NOT NULL,
alter column "password" type VARCHAR(64),
add column if not exists role_id INT NOT null REFERENCES user_role(role_id);

alter table if exists skyview.product
add column if not exists product_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
add column if not exists name VARCHAR(50) NOT NULL,
add column if not exists description VARCHAR,
add column if not exists price DOUBLE PRECISION NOT NULL,
alter column price type double precision,
drop column if exists image_url cascade,
add column if not exists image_url_small VARCHAR,
add column if not exists image_url_medium VARCHAR,
add column if not exists category_id INT NOT null REFERENCES category(category_id);

alter table if exists skyview.product_review
add column if not exists product_review_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
add column if not exists rating INT NOT NULL,
add column if not exists description VARCHAR,
add column if not exists user_id INT NOT null REFERENCES "user"(user_id),
add column if not exists product_id INT NOT null REFERENCES product(product_id);

alter table if exists skyview."order"
add column if not exists order_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
add column if not exists user_id INT REFERENCES "user"(user_id),
add column if not exists address_id INT NOT null REFERENCES address(address_id),
add column if not exists order_status_id INT NOT null REFERENCES order_status(order_status_id);

alter table if exists skyview.order_item
add column if not exists order_id INT NOT null references "order"(order_id),
add column if not exists product_id INT NOT null references product(product_id);





