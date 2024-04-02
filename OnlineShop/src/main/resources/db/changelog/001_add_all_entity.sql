
CREATE TABLE t_user (
                         id serial PRIMARY KEY,
                         username VARCHAR(255) NOT NULL UNIQUE,
                         first_name VARCHAR(255) NOT NULL,
                         last_name VARCHAR(255) NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE t_user_info (
                         id serial PRIMARY KEY,
                         user_id INT,
                         email VARCHAR(255) NOT NULL UNIQUE,
                         address_line VARCHAR(255),
                         city VARCHAR(255) NOT NULL,
                         country VARCHAR(255) NOT NULL,
                         telephone VARCHAR(255) NOT NULL,
                         postal_code VARCHAR(50),
                         FOREIGN KEY (user_id) REFERENCES t_user(id) ON DELETE CASCADE
);

CREATE TABLE t_role (
                         id serial PRIMARY KEY,
                         name VARCHAR(255) NOT NULL UNIQUE,
                         description VARCHAR(255)
);

CREATE TABLE t_user_auth (
                             id serial PRIMARY KEY,
                             password TEXT,
                             user_id INT,
                             role_id INT,
                             token TEXT,
                             expired_date TIMESTAMP NOT NULL,
                             is_expired BOOLEAN NOT NULL,
                             FOREIGN KEY (user_id) REFERENCES t_user(id) ON DELETE CASCADE,
                             FOREIGN KEY (role_id) REFERENCES t_role(id) ON DELETE CASCADE
);

CREATE TABLE t_user_payment (
                                id serial PRIMARY KEY,
                                user_id INT NOT NULL,
                                payment_type VARCHAR(255) NOT NULL,
                                provider VARCHAR(255) NOT NULL,
                                account_no VARCHAR(255) NOT NULL,
                                expiry DATE NOT NULL,
                                FOREIGN KEY (user_id) REFERENCES t_user(id) ON DELETE CASCADE
);

CREATE TABLE t_shopping_session (
                                id serial PRIMARY KEY,
                                user_id INT NOT NULL,
                                total DECIMAL DEFAULT 0,
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                FOREIGN KEY (user_id) REFERENCES t_user(id) ON DELETE CASCADE
);


CREATE TABLE t_product_category (
                                    id serial PRIMARY KEY,
                                    name VARCHAR(255) NOT NULL,
                                    description TEXT NOT NULL,
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    deleted_at TIMESTAMP DEFAULT NULL
);


CREATE TABLE t_product_inventory (
                                    id serial PRIMARY KEY,
                                    quantity INT NOT NULL,
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    deleted_at TIMESTAMP DEFAULT NULL
);

CREATE TABLE t_discount (
                                     id serial PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
                                     description TEXT,
                                     discount_percent DECIMAL NOT NULL,
                                     active BOOLEAN NOT NULL,
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     deleted_at TIMESTAMP DEFAULT NULL,
                                     CHECK (discount_percent between 0 and 100)
);


CREATE TABLE t_product (
                                id serial PRIMARY KEY,
                                name VARCHAR(255) NOT NULL,
                                description TEXT,
                                SKU VARCHAR(255),
                                category_id INT,
                                inventory_id INT,
                                price DECIMAL,
                                discount_id INT,
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                deleted_at TIMESTAMP DEFAULT NULL,
                                FOREIGN KEY (category_id) REFERENCES t_product_category(id) ON DELETE CASCADE,
                                FOREIGN KEY (inventory_id) REFERENCES t_product_inventory(id) ON DELETE CASCADE,
                                FOREIGN KEY (discount_id) REFERENCES t_discount(id) ON DELETE CASCADE
);

CREATE TABLE t_payment_status (
                                id serial PRIMARY KEY,
                                name VARCHAR(255) NOT NULL UNIQUE
);




CREATE TABLE t_cart_item (
                             id serial PRIMARY KEY,
                             session_id INT,
                             product_id INT,
                             quantity INT,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             FOREIGN KEY (session_id) REFERENCES t_shopping_session(id) ON DELETE CASCADE,
                             FOREIGN KEY (product_id) REFERENCES t_product(id) ON DELETE CASCADE
);


CREATE TABLE t_order_details (
                                 id serial PRIMARY KEY,
                                 user_id INT,
                                 total DECIMAL,
                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 FOREIGN KEY (user_id) REFERENCES t_user(id) ON DELETE CASCADE
);

CREATE TABLE t_order_items (
                             id serial PRIMARY KEY,
                             order_id INT,
                             product_id INT,
                             quantity INT,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             FOREIGN KEY (order_id) REFERENCES t_order_details(id) ON DELETE CASCADE,
                             FOREIGN KEY (product_id) REFERENCES t_product(id) ON DELETE CASCADE
);

CREATE TABLE t_payment_details (
                                   id serial PRIMARY KEY,
                                   order_id INT,
                                   amount INT,
                                   provider VARCHAR(255),
                                   status_id INT,
                                   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   FOREIGN KEY (status_id) REFERENCES t_payment_status(id) ON DELETE SET NULL,
                                   FOREIGN KEY (order_id) REFERENCES t_order_details(id) ON DELETE CASCADE
);
