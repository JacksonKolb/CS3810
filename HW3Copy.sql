/*
CS 3810 - Database
Jackson Kolb 
HW3
DUE 10/8/2017 
*/

COPY Carrier(UNIQUE_CARRIER,UNIQUE_CARRIER_ENTITY,UNIQUE_CARRIER_NAME)
FROM 'H:\Jackson\Documents\MSU\CS-3810\Carrier.csv' DELIMITER ',' CSV HEADER;

COPY Carrier_Entity(UNIQUE_CARRIER_ENTITY,CARRIER_GROUP,CARRIER_GROUP_NEW, REGION, AIRLINE_ID)
FROM 'H:\Jackson\Documents\MSU\CS-3810\Carrier_Entity.csv' DELIMITER ',' CSV HEADER;

COPY Carrier_Abreviation(UNIQUE_CARRIER,CARRIER)
FROM 'H:\Jackson\Documents\MSU\CS-3810\Carrier_Abreviation.csv' DELIMITER ',' CSV HEADER;

COPY Carrier_Name(UNIQUE_CARRIER_NAME,CARRIER_NAME)
FROM 'H:\Jackson\Documents\MSU\CS-3810\Carrier_Name.csv' DELIMITER ',' CSV HEADER;

COPY Origin(ORIGIN_STATE_NM,ORIGIN_AIRPORT_ID, ORIGIN)
FROM 'H:\Jackson\Documents\MSU\CS-3810\Origin.csv' DELIMITER ',' CSV HEADER;

COPY Origin_Name(ORIGIN_STATE_NM, ORIGIN_WAC, ORIGIN_STATE_FIPS, ORIGIN_STATE_ABR)
FROM 'H:\Jackson\Documents\MSU\CS-3810\Origin_Name.csv' DELIMITER ',' CSV HEADER;

COPY Origin_Airport(ORIGIN_AIRPORT_SEQ_ID, ORIGIN_AIRPORT_ID, ORIGIN_CITY_MARKET_ID)
FROM 'H:\Jackson\Documents\MSU\CS-3810\Origin_Airport.csv' DELIMITER ',' CSV HEADER;

COPY Origin_City(ORIGIN_CITY_NAME, ORIGIN)
FROM 'H:\Jackson\Documents\MSU\CS-3810\Origin_City.csv' DELIMITER ',' CSV HEADER;

COPY Destination(DEST_STATE_NM, DEST_AIRPORT_ID, DEST)
FROM 'H:\Jackson\Documents\MSU\CS-3810\Destination.csv' DELIMITER ',' CSV HEADER;

COPY Destination_Airport(DEST_AIRPORT_ID, DEST_AIRPORT_SEQ_ID, DEST_CITY_MARKET_ID)
FROM 'H:\Jackson\Documents\MSU\CS-3810\Destination_Airport.csv' DELIMITER ',' CSV HEADER;

COPY Destination_City(DEST_CITY_NAME, DEST)
FROM 'H:\Jackson\Documents\MSU\CS-3810\Destination_City.csv' DELIMITER ',' CSV HEADER;

COPY Destination_Name(DEST_STATE_ABR, DEST_STATE_FIP, DEST_STATE_NM, DEST_WAC)
FROM 'H:\Jackson\Documents\MSU\CS-3810\Destination_Name.csv' DELIMITER ',' CSV HEADER;

COPY Aircraft(AIRCRAFT_GROUP, AIRCRAFT_TYPE, AIRCRAFT_CONFIG, CLASS, DISTANCE, DEPARTURES_SCHEDULED, DEPARTURES_PERFORMED)
FROM 'H:\Jackson\Documents\MSU\CS-3810\Aircraft.csv' DELIMITER ',' CSV HEADER;

COPY Aircraft_Distance(DISTANCE_GROUP, DISTANCE)
FROM 'H:\Jackson\Documents\MSU\CS-3810\Aircraft_Distance.csv' DELIMITER ',' CSV HEADER;

COPY Carrier_Code_Dates(START_DATE_SOURCE, THRU_DATE_SOURCE, UNIQUE_CARRIER_ENTITY)
FROM 'H:\Jackson\Documents\MSU\CS-3810\Carrier_Code_Dates.csv' DELIMITER ',' CSV HEADER;

COPY Aircraft_Passengers(SEATS, PASSENGERS)
FROM 'H:\Jackson\Documents\MSU\CS-3810\Aircraft_Passengers.csv' DELIMITER ',' CSV HEADER;

COPY Aircraft_Airtime(RAMP_TO_RAMP, AIRTIME, DISTANCE)
FROM 'H:\Jackson\Documents\MSU\CS-3810\Aircraft_Airtime.csv' DELIMITER ',' CSV HEADER;

COPY Time_of(YEARS, MONTH, QUARTER)
FROM 'H:\Jackson\Documents\MSU\CS-3810\Time.csv' DELIMITER ',' CSV HEADER;

COPY Aircraft_Seats(SEATS, AIRCRAFT_CONFIG)
FROM 'H:\Jackson\Documents\MSU\CS-3810\Aircraft_Seats.csv' DELIMITER ',' CSV HEADER;

COPY Aircraft_Payload(PAYLOAD, FREIGHT, MAIL)
FROM 'H:\Jackson\Documents\MSU\CS-3810\Aircraft_Payload.csv' DELIMITER ',' CSV HEADER;