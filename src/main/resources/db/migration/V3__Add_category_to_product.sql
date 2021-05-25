ALTER TABLE product ADD COLUMN category_id INTEGER;
ALTER TABLE product ADD CONSTRAINT category_id_fk
	FOREIGN KEY (category_id) REFERENCES category (id);