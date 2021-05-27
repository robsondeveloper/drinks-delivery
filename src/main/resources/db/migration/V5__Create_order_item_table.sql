CREATE TABLE order_item (
    id SERIAL PRIMARY KEY,
    quantity SMALLINT NOT NULL,
    price_per_unit DECIMAL(8,2) NOT NULL,
    order_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    CONSTRAINT uk_order_item_product UNIQUE (order_id, product_id),
    CONSTRAINT fk_order_item_order FOREIGN KEY (order_id) REFERENCES customer_order (id),
    CONSTRAINT fk_order_item_product FOREIGN KEY (product_id) REFERENCES product (id)
);