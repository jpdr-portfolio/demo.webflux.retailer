--DROP TABLE IF EXISTS retailer;
CREATE TABLE IF NOT EXISTS retailer (
  id int AUTO_INCREMENT primary key,
  name VARCHAR(200) NOT NULL,
  sector_id int NOT NULL,
  email VARCHAR(254) NULL,
  address VARCHAR(200) NOT NULL,
  is_active BOOLEAN NOT NULL,
  creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
  deletion_date TIMESTAMP WITH TIME ZONE
);

--DROP TABLE IF EXISTS sector;
CREATE TABLE IF NOT EXISTS sector (
  id int AUTO_INCREMENT primary key,
  name VARCHAR(200) NOT NULL,
  is_active BOOLEAN NOT NULL,
  creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
  deletion_date TIMESTAMP WITH TIME ZONE
);

INSERT INTO sector (name, is_active, creation_date, deletion_date)
SELECT 'Electronics', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM sector WHERE name = 'Electronics');

INSERT INTO sector (name, is_active, creation_date, deletion_date)
SELECT 'Fashion and Apparel', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM sector WHERE name = 'Fashion and Apparel');

INSERT INTO sector (name, is_active, creation_date, deletion_date)
SELECT 'Home and Furniture', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM sector WHERE name = 'Home and Furniture');

INSERT INTO sector (name, is_active, creation_date, deletion_date)
SELECT 'Groceries and Supermarkets', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM sector WHERE name = 'Groceries and Supermarkets');

INSERT INTO sector (name, is_active, creation_date, deletion_date)
SELECT 'Health and Beauty', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM sector WHERE name = 'Health and Beauty');

INSERT INTO sector (name, is_active, creation_date, deletion_date)
SELECT 'Books and Stationery', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM sector WHERE name = 'Books and Stationery');

INSERT INTO sector (name, is_active, creation_date, deletion_date)
SELECT 'Sporting Goods', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM sector WHERE name = 'Sporting Goods');

INSERT INTO sector (name, is_active, creation_date, deletion_date)
SELECT 'Toys and Games', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM sector WHERE name = 'Toys and Games');

INSERT INTO sector (name, is_active, creation_date, deletion_date)
SELECT 'Automotive', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM sector WHERE name = 'Automotive');

INSERT INTO sector (name, is_active, creation_date, deletion_date)
SELECT 'Pet Supplies', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM sector WHERE name = 'Pet Supplies');

INSERT INTO sector (name, is_active, creation_date, deletion_date)
SELECT 'Electronics', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM sector WHERE name = 'Electronics');

INSERT INTO sector (name, is_active, creation_date, deletion_date)
SELECT 'Fashion', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM sector WHERE name = 'Fashion');

INSERT INTO sector (name, is_active, creation_date, deletion_date)
SELECT 'Groceries', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM sector WHERE name = 'Groceries');

INSERT INTO sector (name, is_active, creation_date, deletion_date)
SELECT 'Home Appliances', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM sector WHERE name = 'Home Appliances');

INSERT INTO sector (name, is_active, creation_date, deletion_date)
SELECT 'Luxury Goods', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM sector WHERE name = 'Luxury Goods');

INSERT INTO sector (name, is_active, creation_date, deletion_date)
SELECT 'Sports Equipment', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM sector WHERE name = 'Sports Equipment');

INSERT INTO sector (name, is_active, creation_date, deletion_date)
SELECT 'Books & Stationery', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM sector WHERE name = 'Books & Stationery');

INSERT INTO sector (name, is_active, creation_date, deletion_date)
SELECT 'Toys & Games', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM sector WHERE name = 'Toys & Games');

INSERT INTO sector (name, is_active, creation_date, deletion_date)
SELECT 'Furniture', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM sector WHERE name = 'Furniture');

INSERT INTO sector (name, is_active, creation_date, deletion_date)
SELECT 'Beauty & Personal Care', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM sector WHERE name = 'Beauty & Personal Care');

















INSERT INTO retailer (name, sector_id, email, address, is_active, creation_date, deletion_date)
SELECT 'Tech Haven', (SELECT id FROM sector WHERE name = 'Electronics'), 'info@techhaven.com', 'Santiago, Chile', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM retailer WHERE name = 'Tech Haven');

INSERT INTO retailer (name, sector_id, email, address, is_active, creation_date, deletion_date)
SELECT 'Fashion Fiesta', (SELECT id FROM sector WHERE name = 'Fashion'), 'contact@fashionfiesta.com', 'Madrid, Spain', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM retailer WHERE name = 'Fashion Fiesta');

INSERT INTO retailer (name, sector_id, email, address, is_active, creation_date, deletion_date)
SELECT 'Grocery Galaxy', (SELECT id FROM sector WHERE name = 'Groceries'), 'support@grocerygalaxy.com', 'Lima, Peru', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM retailer WHERE name = 'Grocery Galaxy');

INSERT INTO retailer (name, sector_id, email, address, is_active, creation_date, deletion_date)
SELECT 'Appliance Palace', (SELECT id FROM sector WHERE name = 'Home Appliances'), 'sales@appliancepalace.com', 'Bogotá, Colombia', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM retailer WHERE name = 'Appliance Palace');

INSERT INTO retailer (name, sector_id, email, address, is_active, creation_date, deletion_date)
SELECT 'Luxury Lane', (SELECT id FROM sector WHERE name = 'Luxury Goods'), 'contact@luxurylane.com', 'São Paulo, Brazil', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM retailer WHERE name = 'Luxury Lane');

INSERT INTO retailer (name, sector_id, email, address, is_active, creation_date, deletion_date)
SELECT 'Sporty Spree', (SELECT id FROM sector WHERE name = 'Sports Equipment'), 'hello@sportyspree.com', 'Caracas, Venezuela', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM retailer WHERE name = 'Sporty Spree');

INSERT INTO retailer (name, sector_id, email, address, is_active, creation_date, deletion_date)
SELECT 'Book Nook', (SELECT id FROM sector WHERE name = 'Books & Stationery'), 'info@booknook.com', 'Mexico City, Mexico', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM retailer WHERE name = 'Book Nook');

INSERT INTO retailer (name, sector_id, email, address, is_active, creation_date, deletion_date)
SELECT 'Toy Town', (SELECT id FROM sector WHERE name = 'Toys & Games'), 'support@toytown.com', 'Quito, Ecuador', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM retailer WHERE name = 'Toy Town');

INSERT INTO retailer (name, sector_id, email, address, is_active, creation_date, deletion_date)
SELECT 'Furniture Fusion', (SELECT id FROM sector WHERE name = 'Furniture'), 'sales@furniturefusion.com', 'Buenos Aires, Argentina', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM retailer WHERE name = 'Furniture Fusion');

INSERT INTO retailer (name, sector_id, email, address, is_active, creation_date, deletion_date)
SELECT 'Beauty Bazaar', (SELECT id FROM sector WHERE name = 'Beauty & Personal Care'), 'contact@beautybazaar.com', 'Montevideo, Uruguay', true, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM retailer WHERE name = 'Beauty Bazaar');