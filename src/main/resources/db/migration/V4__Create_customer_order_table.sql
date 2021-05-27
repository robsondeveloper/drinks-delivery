CREATE TABLE customer_order (
    id SERIAL PRIMARY KEY,
    total DECIMAL(8,2) NOT NULL,
    status CHARACTER VARYING(32) NOT NULL,
    created_at TIMESTAMP NOT NULL
);