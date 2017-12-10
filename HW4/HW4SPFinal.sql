/*
CS 3810 - Database
Max Morrison and Jackson Kolb
Collaborated with Emily to finish SP after walking through HW3 and HW4 queries 
HW4 Stored Procedure Final
*/


-- CoCo is an abrevieatoin for Correlation Coefficient

CREATE OR REPLACE FUNCTION flightData(selected_carrier varchar(80)) 
RETURNS TABLE(
    carrierName character varying(80),
    flightTotal bigint,
    distanceAverageRatio numeric,
    passengerDistanceRatio numeric,
    freightDistanceRatio numeric,
    freightPassengerCoCo double precision, 
    passengerDistanceCoCo double precision
    
) AS $$
BEGIN 
    RETURN QUERY 
    SELECT DISTINCT
    carrier.unique_carrier_name AS carrierName,
    sum(origin.departures_performed) AS flightTotal,
    avg(od.distance) AS distanceAverageRatio,
    1.0 * sum(origin.passengers) / sum(od.distance) AS passengerDistanceRatio,
    1.0 * sum(origin.freight) / sum(od.distance) AS freightDistanceRatio,
    corr(origin.passengers, origin.freight) AS passengerDistanceCoCo,
    corr(origin.passengers, od.distance) AS freightPassengerCoCo
    FROM flights origin, flights dest, 
    distance od, distance dd, carrier
    WHERE
    origin.origin_airport_id = od.origin_airport_id
    AND dest.dest_airport_id = dd.dest_airport_id
    AND carrier.unique_carrier_entity = origin.unique_carrier_entity
    AND carrier.unique_carrier_name = selected_carrier
    GROUP BY carrier.unique_carrier_name;
END;
$$ LANGUAGE plpgsql VOLATILE;

SELECT flightData('JetBlue Airways');
SELECT flightData('Virgin America');
SELECT flightData('Tradewind Aviation');
SELECT flightData('Aerodynamics Inc. d/b/a SkyValue d/b/a SkyValue Airways');
SELECT flightData('Ameristar Air Cargo');
SELECT flightData('Allegiant Air');
SELECT flightData('Avjet Corporation');
SELECT flightData('American Airlines Inc.');
SELECT flightData('Delta Air Lines Inc.');
SELECT flightData('Miami Air International');
SELECT flightData('United Air Lines Inc.');
SELECT flightData('Southwest Airlines Co.');
SELECT flightData('Jet Aviation Flight Services, Inc.');
SELECT flightData('Omni Air International LLC');
SELECT flightData('Swift Air, LLC');
SELECT flightData('Kalitta Charters II');
SELECT flightData('CFM Inc d/b/a Contour Airlines');
SELECT flightData('Sun Country Airlines d/b/a MN Airlines');
SELECT flightData('USA Jet Airlines Inc.');
SELECT flightData('Southern Air Inc.');
SELECT flightData('Alaska Airlines Inc.');
SELECT flightData('Trans States Airlines');
SELECT flightData('Peninsula Airways Inc.');
SELECT flightData('Great Lakes Airlines');
SELECT flightData('Boutique Air');
SELECT flightData('GoJet Airlines LLC d/b/a United Express');
SELECT flightData('SkyWest Airlines Inc.');
SELECT flightData('Republic Airlines');
SELECT flightData('Frontier Airlines Inc.');
SELECT flightData('Federal Express Corporation');
SELECT flightData('Spirit Air Lines');
SELECT flightData('Mesa Airlines Inc.');
SELECT flightData('Envoy Air');
SELECT flightData('ExpressJet Airlines Inc.');
SELECT flightData('United Parcel Service');
SELECT flightData('Endeavor Air Inc.');
SELECT flightData('Compass Airlines');
SELECT flightData('Via Airlines d/b/a Charter Air Transport');
SELECT flightData('Horizon Air');
SELECT flightData('Empire Airlines Inc.');
