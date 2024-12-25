INSERT INTO CarModel (id, brand, model, countryOrigin, countryCode) VALUES
(1, 'Toyota', 'Camry', 'Japan', 'JP'),
(2, 'Volkswagen', 'Golf', 'Germany', 'DE'),
(3, 'Ford', 'Mustang', 'USA', 'US');

INSERT INTO Dealership (name) VALUES
('Super Cars'),
('Elite Motors'),
('Luxury Autos');

INSERT INTO Car (id, carModelId, dealershipName, state, configuration, color, price) VALUES
(1, 1, 'Super Cars', 'New', 'Standard', 'Black', 30000),
(2, 2, 'Elite Motors', 'Used', 'Premium', 'Red', 25000),
(3, 3, 'Luxury Autos', 'New', 'Standard', 'Blue', 45000);
