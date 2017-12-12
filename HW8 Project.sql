/*
Max Morrison & Jackson Kolb
CS 3810
HW8 GIS
This is the postal codes project
We put the classroster db in the usa schema so we could add the postal codes over it
*/

--Copy the postal code data from the provided file into the usa postal code table
COPY usa.postalcodes FROM 'C:\School\Semester4Fall2017\CS3810\HW8\data'
WITH (FORMAT csv, HEADER True, QUOTE '"');

--This creates the points for the postal codes
SELECT AddGeometryColumn('usa','postalcodes','geom',0,'POINT',2);

--Insert classroster
INSERT INTO usa.classroster (last,first,postalcode) VALUES ('Cain','David',75001);
INSERT INTO usa.classroster (last,first,postalcode) VALUES ('Coryell','Christian',56301);
INSERT INTO usa.classroster (last,first,postalcode) VALUES ('Narkewicz','David',05060);
INSERT INTO usa.classroster (last,first,postalcode) VALUES ('Santons','Edirlei',92354);
INSERT INTO usa.classroster (last,first,postalcode) VALUES ('Morrison','Max',80112);
INSERT INTO usa.classroster (last,first,postalcode) VALUES ('Kolb','Jackson',80222);

--Update the postal code data so it can be viewed... We guess, instructions weren't clear on why they tipped is this one
UPDATE usa.postalcodes
SET geom = 'POINT(' || postalcodes.long|| ' ' || postalcodes.lat || ')';

/*
Using the query builder in QGIS we created the layer witht the points for the postal codes. Here is that code
*/

"code"  LIKE  '%75001%'  
OR  "code"  LIKE '%56301%'
OR  "code"  LIKE '%05060%' 
OR  "code"  LIKE '%92354%'
OR  "code"  LIKE '%80112%'
OR  "code"  LIKE '%80222%'