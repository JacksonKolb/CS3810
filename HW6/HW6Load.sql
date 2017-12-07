/*
CS 3810 - Database
Jackson Kolb and Max Morrison
HW5
DUE 10/26/2017 
*/

CREATE TABLE Payer (
	firstName VARCHAR(20),
	lastName VARCHAR(20),
	email VARCHAR(100) PRIMARY KEY
);

CREATE TABLE PayerAddress (
	payerCity VARCHAR(30),
	payerState VARCHAR(30),
	payerCountry VARCHAR(30),
	zipCode INT,
	payerStreetAddress VARCHAR(100),
	email VARCHAR(100) references Payer(email)
);

CREATE TABLE PayerPhoneNumbers(
	countryCode INT,
	localCode INT, 
	localNumber INT,
	email VARCHAR(100),
	FOREIGN KEY(email) references Payer(email)
);

CREATE TABLE Cities(
	cityName VARCHAR(100),
	state VARCHAR(50),
	uniqueCityCode INT PRIMARY KEY
);

CREATE TABLE Airlines(
	airlineName VARCHAR(100),
	uniqueAirlineCode INT PRIMARY KEY
);

CREATE TABLE Flights(
	uniqueFlightNumber INT PRIMARY KEY,
	lengthOfFlight INT,
	uniqueAirlineCode INT references Airlines(uniqueAirlineCode),
	originCode INT references Cities(uniqueCityCode),
	destCode INT references Cities(uniqueCityCode),
	arrivalDate VARCHAR(50),
	arrivalHour INT,
	arrivalMin INT,
	departureDate VARCHAR(50),
	departureHour INT,
	departureMinute INT	
);

CREATE TABLE Booking( 
	numOfPassengers INT,
	bookingNumber INT PRIMARY KEY,
	bookingOriginID INT,
	bookingDate VARCHAR(50),
	payerFirstName VARCHAR(40),
	payerLastName VARCHAR(40),
	payerEmail VARCHAR(100) references Payer(email)
);

CREATE TABLE Passenger(
	payerEmail VARCHAR(100) references Payer(Email),
	bookingNumber INT references Booking(bookingNumber),
	uniqueFlightNumber INT references Flights(uniqueFlightNumber),
	passFirstName VARCHAR(50),
	passLastName VARCHAR(50),
	passEmail VARCHAR(100),
	passID INT PRIMARY KEY
);

CREATE TABLE PassengerAddress(
	passCountry VARCHAR(25),
	passCity VARCHAR(25),
	passState VARCHAR(25),
	passPostalCode INT,
	passStreetAddress VARCHAR(100),
	email VARCHAR(100) references Payer(email)
);

CREATE TABLE PassengerPhoneNumbers(
	countryCode INT,
	localCode INT,  
	localNumber INT,
	email VARCHAR(100),
	FOREIGN KEY(email) references Payer(email)
);

CREATE TABLE Tickets(
	uniqueFlightNumber INT references Flights(uniqueFlightNumber),
	bookingNumber INT references Booking(bookingNumber),
	passID INT references Passenger(passID)
	
);

CREATE TABLE Airports(
	airportCode INT,
	airportName VARCHAR(50),
	cityName VARCHAR(50),
	stateName VARCHAR(50),
	PRIMARY KEY (airportCode)
);
