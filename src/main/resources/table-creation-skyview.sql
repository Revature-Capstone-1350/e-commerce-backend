CREATE SCHEMA skyview AUTHORIZATION postgres;

set search_path to skyview;

CREATE TABLE IF NOT EXISTS user_role (
	role_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
	name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS order_status (
	order_status_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
	name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS address (
	address_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
	street VARCHAR NOT NULL,
	street2 VARCHAR,
	state VARCHAR(2) NOT NULL,
	city VARCHAR(50) NOT NULL,
	postal_code VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS category (
	category_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
	name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS "user" (
	user_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50),
	email VARCHAR(255) NOT NULL UNIQUE,
	"password" VARCHAR(64) NOT NULL,
	role_id INT NOT NULL,
	
	CONSTRAINT fk_user_role 
		FOREIGN KEY (role_id)
			REFERENCES user_role(role_id)
);

CREATE TABLE IF NOT EXISTS product (
	product_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
	name VARCHAR(50) NOT NULL,
	description VARCHAR,
	price DOUBLE PRECISION NOT NULL,
	image_url_small VARCHAR NOT NULL,
	image_url_medium VARCHAR NOT NULL,
	category_id INT NOT NULL,
	
	CONSTRAINT fk_category
		FOREIGN KEY (category_id)
			REFERENCES category(category_id)
);

CREATE TABLE IF NOT EXISTS product_review (
	product_review_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
	rating INT NOT NULL,
	description VARCHAR,
	user_id INT NOT NULL,
	product_id INT NOT NULL,
	
	CONSTRAINT fk_user
		FOREIGN KEY (user_id)
			REFERENCES "user"(user_id),
	CONSTRAINT fk_product
		FOREIGN KEY (product_id)
			REFERENCES product(product_id)
);

CREATE TABLE IF NOT EXISTS "order" (
	order_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
	user_id INT,
	address_id INT NOT NULL,
	order_status_id INT NOT NULL,
	
	CONSTRAINT fk_user
		FOREIGN KEY (user_id)
			REFERENCES "user"(user_id),
	CONSTRAINT fk_address
		FOREIGN KEY (address_id)
			REFERENCES address(address_id),
	CONSTRAINT fk_order_status
		FOREIGN KEY (order_status_id)
			REFERENCES order_status(order_status_id)
);

CREATE TABLE IF NOT EXISTS order_item (
	order_id INT NOT NULL,
	product_id INT NOT NULL,
	
	CONSTRAINT order_item_pkey PRIMARY KEY (order_id, product_id),
	constraint fk_order 
		foreign key (order_id)
			references "order"(order_id),
	constraint fk_product
		foreign key (product_id)
			references product(product_id)
);