CREATE TABLE CarModel (
    id INT PRIMARY KEY,
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    countryOrigin VARCHAR(255),
    countryCode VARCHAR(10)
);

CREATE TABLE Dealership (
    name VARCHAR(255) PRIMARY KEY
);

CREATE TABLE Car (
    id INT PRIMARY KEY,
    carModelId INT,
    dealershipName VARCHAR(255),
    state VARCHAR(50),
    configuration VARCHAR(255),
    color VARCHAR(50),
    price DOUBLE,
    FOREIGN KEY (carModelId) REFERENCES CarModel(id),
    FOREIGN KEY (dealershipName) REFERENCES Dealership(name)
);
