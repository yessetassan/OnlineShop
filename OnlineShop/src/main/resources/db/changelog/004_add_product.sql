-- Insert data into t_product_category
INSERT INTO t_product_category (name, description, created_at, modified_at)
VALUES
    ('Books', 'Category for books', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Electronics', 'Category for electronics', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Clothing', 'Category for clothing', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert data into t_product_inventory
INSERT INTO t_product_inventory (quantity, created_at, modified_at)
VALUES
    (100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (50, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (200, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert data into t_discount
INSERT INTO t_discount (name, description, discount_percent, active, created_at, modified_at)
VALUES
    ('Summer Sale', 'Discount for summer season', 20.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Clearance Sale', 'Discount for clearance items', 30.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Holiday Sale', 'Discount for holiday season', 15.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert data into t_product
INSERT INTO t_product (name, description, SKU, category_id, inventory_id, price, discount_id, created_at, modified_at)
VALUES
    ('Book 1', 'Description of Book 1', 'SKU123', 1, 1, 25.00, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Laptop', 'Description of Laptop', 'SKU456', 2, 2, 800.00, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('T-shirt', 'Description of T-shirt', 'SKU789', 3, 3, 15.00, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
