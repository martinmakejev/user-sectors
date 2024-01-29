INSERT INTO sector (name) VALUES
('Manufacturing'),
('Other'),
('Service');

INSERT INTO sector (name, parent_id) VALUES
('Construction materials', 1),
('Electronics and Optics', 1),
('Food and Beverage', 1),
('Bakery & confectionery products', 6),
('Beverages', 6),
('Fish & fish products', 6),
('Meat & meat products', 6),
('Milk & dairy products', 6),
('Other', 6),
('Sweets & snack food', 6),
('Furniture', 1),
('Bathroom/sauna', 14),
('Bedroom', 14),
('Children’s room', 14),
('Kitchen', 14),
('Living room', 14),
('Office', 14),
('Other (Furniture)', 14),
('Outdoor', 14),
('Project furniture', 14),
('Machinery', 1),
('Machinery components', 24),
('Machinery equipment/tools', 24),
('Manufacture of machinery', 24),
('Maritime', 24),
('Aluminium and steel workboats', 28),
('Boat/Yacht building', 28),
('Ship repair and conversion', 28),
('Metal structures', 24),
('Other', 24),
('Repair and maintenance service', 24),
('Metalworking', 1),
('Construction of metal structures', 35),
('Houses and buildings', 35),
('Metal products', 35),
('Metal works', 35),
('CNC-machining', 39),
('Forgings, Fasteners', 39),
('Gas, Plasma, Laser cutting', 39),
('MIG, TIG, Aluminum welding', 39),
('Plastic and Rubber', 1),
('Packaging', 44),
('Plastic goods', 44),
('Plastic processing technology', 44),
('Blowing', 47),
('Moulding', 47),
('Plastics welding and processing', 47),
('Plastic profiles', 47),
('Printing', 1),
('Advertising', 52),
('Book/Periodicals printing', 52),
('Labelling and packaging printing', 52),
('Textile and Clothing', 1),
('Clothing', 56),
('Textile', 56),
('Wood', 1),
('Other (Wood)', 59),
('Wooden building materials', 59),
('Wooden houses', 59);

INSERT INTO sector (name, parent_id) VALUES
('Creative industries', 2),
('Energy technology', 2),
('Environment', 2);

INSERT INTO sector (name, parent_id) VALUES
('Business services', 3),
('Engineering', 3),
('Information Technology and Telecommunications', 3),
('Data processing, Web portals, E-marketing', 68),
('Programming, Consultancy', 68),
('Software, Hardware', 68),
('Telecommunications', 68),
('Tourism', 3),
('Translation services', 3),
('Transport and Logistics', 3),
('Air', 75),
('Rail', 75),
('Road', 75),
('Water', 75);