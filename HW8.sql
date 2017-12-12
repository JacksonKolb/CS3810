/*
Max Morrison & Jackson Kolb
CS 3810
HW8 GIS
This file contains all of the sql commands we wrote for this project
It starts with the queries
usa schema and census2010 table created in setup instructions
from the assignment. CSV loaded using the pgAdmin4 instructions.
*/

--Select the states with a 2008 population over 10 million.
SELECT name, pop2008
FROM usa.states
WHERE pop2008 > 10000000;

--Select the state capitals.
SELECT name, stateabb
FROM usa.cities
WHERE capital = 1;

--Select the states whose names begin with the word "New".
SELECT name
FROM usa.states
WHERE name LIKE '%New%';

--Select the cities whose names contain the letter "z".
SELECT name
FROM usa.cities
WHERE name LIKE '%z%'
	OR name LIKE '%Z%';

--Sort the states by their 2008 population from high to low.
SELECT name, pop2008
FROM usa.states
ORDER BY pop2008 DESC;

--Sort the states first by soft drink name then by state name.
SELECT name, sub_region
FROM usa.states
ORDER BY sub_region, name;

--Select the states with a 2008 population over 10 million and where the majority of population refers to soft drinks as pop.
SELECT name, sub_region
FROM usa.states
WHERE pop2008 > 10000000
AND sub_region LIKE 'Pop';

--Select cities in the states of NY, NJ and PA (using the stateabb column).
SELECT name, stateabb
FROM usa.cities
WHERE stateabb LIKE '%US-NY%'
	OR stateabb LIKE '%US-NJ%'
	OR stateabb LIKE '%US-PA%';

--For each state compute the percentage of the 2010 population that is white. Give this output column an alias of pctwhite. Besides pctwhite include only the name of the state in the output.
--Note: the columns involved in this calculation are defined as integers, which means the resulting value will be rounded to the nearest integer (0). To avoid this rounding and obtain the desired percentages,
--add '::double precision' after the white column in the calculation. This will convert the integer values to double precision values prior to the calculation. 
--It is only necessary to perform this conversion for one of the columns involved in the calculation.
SELECT state, (white::double precision/total) * 100 
AS pctwhite 
FROM census2010;

--Sum the 2008 state populations across the soft drink categories (i.e., What is the population of the 'soda' states? Of the 'pop' states? Of the 'coke' states?)
SELECT sub_region, Sum(pop2008) 
FROM usa.states 
GROUP BY sub_region;

--Bring together data from the states and census2010 tables, outputting the name from the states table, total population from the census2010 table and geom from the states table.
SELECT states.name, census2010.total, states.geom
FROM usa.states 
INNER JOIN census2010
ON states.name = census2010.state;

--Calculate the average 2010 male population across the soft drink categories.
SELECT states.sub_region, Avg(census2010.male)
FROM usa.states 
INNER JOIN usa.census2010
ON states.name = census2010.state
GROUP BY states.sub_region;


/*
Spatial Select Queries
Other than the in pgAdmin defined columns here are the statements we entered
*/

SET search_path TO nyc_poi, public;

--pts DB

SELECT AddGeometryColumn('nyc_poi','pts','geom',4269,'POINT',2);

INSERT INTO pts (name, geom)
VALUES ('Empire State Building', ST_GeomFromText('POINT(-73.985744 40.748549)',4269));

INSERT INTO pts (name, geom)
VALUES ('Statue of Liberty', ST_GeomFromText('POINT(-74.044508 40.689229)',4269));

INSERT INTO pts (name, geom)
VALUES ('World Trade Center', ST_GeomFromText('POINT(-74.013371 40.711549)',4269));

INSERT INTO pts (name, geom)
VALUES ('Radio City Music Hall', ST_GeomFromText('POINT(-73.97988 40.760171)',4269)),
('Madison Square Garden', ST_GeomFromText('POINT(-73.993544 40.750541)',4269));

--lines DB

SELECT AddGeometryColumn('nyc_poi','lines','geom',4269,'LINESTRING',2);

INSERT INTO lines (name, geom)
VALUES ('Holland Tunnel',ST_GeomFromText('LINESTRING(
-74.036486 40.730121,
-74.03125 40.72882,
-74.011123 40.725958)',4269)),
('Lincoln Tunnel',ST_GeomFromText('LINESTRING(
-74.019921 40.767119,
-74.002841 40.759773)',4269)),
('Brooklyn Bridge',ST_GeomFromText('LINESTRING(
-73.99945 40.708231,
-73.9937 40.703676)',4269));

-- poly DB

SELECT AddGeometryColumn('nyc_poi','polys','geom',4269,'POLYGON',2);

INSERT INTO polys (name, geom)
VALUES ('Central Park',ST_GeomFromText('POLYGON((
-73.973057 40.764356,
-73.981898 40.768094,
-73.958209 40.800621,
-73.949282 40.796853,
-73.973057 40.764356))',4269));

INSERT INTO polys (name, geom)
VALUES ('Central Park',ST_GeomFromText('POLYGON((
-73.973057 40.764356,
-73.981898 40.768094,
-73.958209 40.800621,
-73.949282 40.796853,
-73.973057 40.764356),
(-73.966681 40.785221,
-73.966058 40.787674,
-73.965586 40.788064,
-73.9649 40.788291,
-73.963913 40.788194,
-73.963333 40.788291,
-73.962539 40.788259,
-73.962153 40.788389,
-73.96181 40.788714,
-73.961359 40.788909,
-73.960887 40.788925,
-73.959986 40.788649,
-73.959492 40.788649,
-73.958913 40.78873,
-73.958269 40.788974,
-73.957797 40.788844,
-73.957497 40.788568,
-73.957497 40.788259,
-73.957776 40.787739,
-73.95784 40.787057,
-73.957819 40.786569,
-73.960801 40.782394,
-73.961145 40.78215,
-73.961638 40.782036,
-73.962518 40.782199,
-73.963076 40.78267,
-73.963677 40.783661,
-73.965694 40.784457,
-73.966681 40.785221)
)',4269));

--mixed DB

SELECT AddGeometryColumn('nyc_poi','mixed','geom',4269,'GEOMETRY',2);

INSERT INTO mixed (name, geom)
VALUES ('Empire State Building', ST_GeomFromText('POINT(-73.985744 40.748549)',4269)),
('Statue of Liberty', ST_GeomFromText('POINT(-74.044508 40.689229)',4269)),
('World Trade Center', ST_GeomFromText('POINT(-74.013371 40.711549)',4269)),
('Radio City Music Hall', ST_GeomFromText('POINT(-73.97988 40.760171)',4269)),
('Madison Square Garden', ST_GeomFromText('POINT(-73.993544 40.750541)',4269)),
('Holland Tunnel',ST_GeomFromText('LINESTRING(
-74.036486 40.730121,
-74.03125 40.72882,
-74.011123 40.725958)',4269)),
('Lincoln Tunnel',ST_GeomFromText('LINESTRING(
-74.019921 40.767119,
-74.002841 40.759773)',4269)),
('Brooklyn Bridge',ST_GeomFromText('LINESTRING(
-73.99945 40.708231,
-73.9937 40.703676)',4269)),
('Central Park',ST_GeomFromText('POLYGON((
-73.973057 40.764356,
-73.981898 40.768094,
-73.958209 40.800621,
-73.949282 40.796853,
-73.973057 40.764356))',4269));

-- QGIS is section 4, file on GitHub

/*
Working with Views section
*/

--Used as view definition
SELECT * FROM usa.cities WHERE capital = 1 ORDER BY stateabb;

-- Query based on a view
SELECT * FROM usa.vw_capitals WHERE popclass = 2;

--View based on spatial function
SELECT gid, name, pop2008, sub_region, ST_Transform(geom,2163) AS geom FROM usa.states;

--and again
SELECT *, ST_Transform(geom,2163) AS geom_2163 FROM usa.cities;


--Section 6 is Spatial refrences

SELECT srid, srtext FROM spatial_ref_sys
WHERE srtext LIKE '%Pennsylvania%';