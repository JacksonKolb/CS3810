/*
CS 3810 - Database
Jackson Kolb and Max Morrison (Help from Keira)
HW6
DUE Before 12/7 
*/



package flightquery;

import java.util.*;
import java.io.*;
import java.sql.*;

public class FlightScheduler {

    private static final Scanner USERINPUT = new Scanner(System.in);
    static Connection conn = null;
    static ResultSet rs = null;
    static Statement statement = null;
    private static final String REGEXSTR = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?"
            + "[0-9]{4}$";
    private static String firstName, lastName, streetAddress, city, state,
            country, email, passFirstName, passLastName, passStreetAddress,
            passCity, passState, passCountry, passEmail, airlineName,
            airlineCode, airlineCountry, departureDate, arrivalDate,
            airportName, airportCity, airportState, bookingDate, username, password;
    private static int zipCode, countryCode, localCode, localNumber,
            bookingNumber, uniqueFlightNumber, passID, passZipCode,
            originCode, destinationCode, length, departureHour, departureMinute,
            arrivalHour, arrivalMin, airportCode, bookingCityCode,
            numOfPassengers;

    public static void main(String[] args) throws IOException {
        UI();
        insertPayerData();
        insertPassengerData();
        makeReservation();
        getConfirmation();
    }

    private static void UI() {
        try {
            boolean error = false;
            System.out.println("PostgreSQL Login");
            System.out.println("----------------");
            System.out.print("Username: ");
            username = USERINPUT.nextLine();
            System.out.print("Password: ");
            password = USERINPUT.nextLine();
            PrintWriter outFile = new PrintWriter("HW6Error.txt");
            System.out.println("---------------------------");
            System.out.println(" Flight Reservation System ");
            System.out.println("---------------------------");
            System.out.print("First Name: ");
            firstName = USERINPUT.nextLine();
            System.out.print("Last Name: ");
            lastName = USERINPUT.nextLine();
            System.out.print("Street Address "
                    + "(do not include city, state, or zip): ");
            streetAddress = USERINPUT.nextLine();
            System.out.print("City: ");
            city = USERINPUT.nextLine();
            System.out.print("State: ");
            state = USERINPUT.nextLine();
            System.out.print("Country: ");
            country = USERINPUT.nextLine();
            System.out.print("Phone number information: ");
            System.out.println("Country code:");
            countryCode = Integer.parseInt(USERINPUT.nextLine());
            System.out.println("Area code:");
            localCode = Integer.parseInt(USERINPUT.nextLine());
            System.out.println("Local number:");
            localNumber = Integer.parseInt(USERINPUT.nextLine());
            System.out.print("5 Digit Zip Code: ");
            zipCode = Integer.parseInt(USERINPUT.nextLine());
            System.out.print("Email Address: ");
            email = USERINPUT.nextLine();

            if (!(isAlpha(firstName))) {
                outFile.println(firstName + " is not a valid name. "
                        + "a-z, A-Z only.");
                error = true;
            }
            if (!(isAlpha(lastName))) {
                outFile.println(lastName + " is not a valid name. "
                        + "a-z, A-Z only.");
                error = true;
            }
            if (!(isAlpha(city))) {
                outFile.println(city + " is not a valid city. a-z, A-Z only.");
                error = true;
            }
            if (!(isAlpha(state))) {
                outFile.println(state + " is not a valid state. "
                        + "a-z, A-Z only.");
                error = true;
            }
            if (!(email.contains("@"))) {
                outFile.println(email + " is not a valid email. "
                        + "It must contain an @ character.");
                error = true;
            }
            if (!(Integer.toString(zipCode).length() == 5)) {
                outFile.println(zipCode + " is not a valid zipCode. "
                        + "It must have of a length of 5.");
                error = true;
            }
            if (error) {
                outFile.close();
                System.exit(0);
            }
            System.out.print("Enter the number of passengers: ");
            numOfPassengers = Integer.parseInt(USERINPUT.nextLine());
            for (int i = 0; i < numOfPassengers; i++) {
                System.out.print("Passenger First Name: ");
                passFirstName = USERINPUT.nextLine();
                System.out.print("Passenger Last Name: ");
                passLastName = USERINPUT.nextLine();
                System.out.print("Passenger Street Address "
                        + "(do not include city, state, or zip): ");
                passStreetAddress = USERINPUT.nextLine();
                System.out.print("Passenger City: ");
                passCity = USERINPUT.nextLine();
                System.out.print("Passenger State Abbreviation: ");
                passState = USERINPUT.nextLine();
                System.out.print("Passenger 5 Digit Zip Code: ");
                passZipCode = Integer.parseInt(USERINPUT.nextLine());
                System.out.print("Passenger Country: ");
                passCountry = USERINPUT.nextLine();
                System.out.println("Country code:");
                countryCode = Integer.parseInt(USERINPUT.nextLine());
                System.out.println("Area code:");
                localCode = Integer.parseInt(USERINPUT.nextLine());
                System.out.println("Local number:");
                localNumber = Integer.parseInt(USERINPUT.nextLine());
                System.out.print("Passenger Email address:");
                passEmail = USERINPUT.nextLine();

                if (!(passEmail.contains("@"))) {
                    outFile.println(email + " is not a valid email. "
                            + "It must contain an @ character.");
                    error = true;
                }
                if (!(Integer.toString(passZipCode).length() == 5)) {
                    outFile.println(passZipCode + " is not a valid zipCode. "
                            + "It must have of a length of 5.");
                    error = true;
                }
                if (error) {
                outFile.close();
                System.exit(0);
            }
            }

        } catch (FileNotFoundException e) {
            System.err.println(" File not found ");
        }
    }

    public static boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }

    private static void openConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    private static void insertPayerData() {
        try {
            openConnection();
            conn.setAutoCommit(false);
            statement = conn.createStatement();
            String insertPayer = "INSERT INTO Payers (Email, firstName, lastName) +"
                    + "VALUES ('" + email + "', '" + firstName + "', '" + lastName + ",)";
            statement.executeUpdate(insertPayer);
            String insertPayerPhone = "INSERT INTO PayerPhoneNumbers "
                    + "(countryCode, localCode, localNumber) +"
                    + "VALUES ('" + countryCode + "', '" + localCode + "', '" + localNumber + ",)";
            statement.executeUpdate(insertPayerPhone);
            String insertPayerAddress = "INSERT INTO PayerAddress (payerCountry,"
                    + " payerCity, payerState, payerPostalCode, payerStreesAddress) +"
                    + "VALUES ('" + country + "', '" + city + "', '" + state + ","
                    + zipCode + "', '" + streetAddress + "',)";
            statement.executeUpdate(insertPayerAddress);
            statement.close();
            conn.commit();
            conn.close();
        } catch (SQLException exception) {
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
            System.exit(0);
        }
    }

    private static void insertPassengerData() {
        try {
            openConnection();
            conn.setAutoCommit(false);
            statement = conn.createStatement();
            String insertPassenger = "INSERT INTO Passenger (passFirstName, "
                    + "passLastName, passEmail) +"
                    + "VALUES ('" + passFirstName + "', '" + passLastName + "', '" + passEmail + ",)";
            statement.executeUpdate(insertPassenger);
            String insertPassengerPhone = "INSERT INTO PassengerPhoneNumbers "
                    + "(countryCode, localCode, localNumber) +"
                    + "VALUES ('" + countryCode + "', '" + localCode + "', '" + localNumber + ",)";
            statement.executeUpdate(insertPassengerPhone);
            String insertPassengerAddress = "INSERT INTO PassengerAddress (passCountry,"
                    + " passCity, passState, passPostalCode, passStreesAddress) +"
                    + "VALUES ('" + passCountry + "', '" + passCity + "', '" + passState + ","
                    + passZipCode + "', '" + passStreetAddress + "',)";
            statement.executeUpdate(insertPassengerAddress);
            statement.close();
            conn.commit();
            conn.close();
        } catch (SQLException exception) {
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
            System.exit(0);
        }
    }

    private static void makeReservation() {
        System.out.print("From what city would your flight originate?: ");
        String origin = USERINPUT.nextLine();
        try {
            openConnection();
            conn.setAutoCommit(false);
            statement = conn.createStatement();
            rs = statement.executeQuery("SELECT * FROM Flights WHERE (originCode = '" + originCode + "')");
            if (!rs.isBeforeFirst()) {
                System.out.println("Sorry, that's not a valid origin city, please restart program.");
                System.exit(0);
            } else {
                System.out.println("Here is a list of flights that originate in " + originCode);
                System.out.println("-------------------------------------------------------------");
            }
            while (rs.next()) {
                String airline = rs.getString("airlineName");
                int flightNumber = rs.getInt("uniqueFlightNumber");
                String originCity = rs.getString("originCode");
                String destinationCity = rs.getString("destinationCode");
                System.out.println(airline + " flight " + flightNumber + " flies from " + originCity + " to "
                        + destinationCity);
            }
            rs.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        int counter = 0;
        do {
            System.out.print("Choose a destination city from the above list: ");
            String destCode = USERINPUT.nextLine();
            try {
                openConnection();
                conn.setAutoCommit(false);
                statement = conn.createStatement();
                rs = statement.executeQuery("SELECT * FROM Flights WHERE (originCode = '" + originCode + "' AND destinationCode = '"
                        + destCode + "')");
                if (!rs.isBeforeFirst()) {
                    System.out.print("Sorry, that's not a valid destination. ");
                    counter++;
                    if (counter == 3) {
                        System.out.println("Maximum attempts reached, goodbye!");
                        rs.close();
                        statement.close();
                        conn.close();
                        System.exit(0);
                    }
                } else {
                    rs.next();
                    int flightNumber = rs.getInt("Flight_number");
                    break;
                }
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        } while (counter < 3);
        try {
            rs.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
     private static void getConfirmation() throws IOException{
        FileWriter fileWriter = new FileWriter("HW6.flight", true);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        try {
            openConnection();
            conn.setAutoCommit(false);
            statement = conn.createStatement();
            rs = statement.executeQuery("SELECT * FROM Flights WHERE uniqueFlightNumber = "+uniqueFlightNumber+"");
            rs.next();
            String airline = rs.getString("airlineName");
            String origin = rs.getString("originCode");
            String destination = rs.getString("destinationCode");
            String departDate = rs.getString("departureDate");
            String departHour = rs.getString("departureHour");
            String departMin = rs.getString("departureMin");
            String arrivalHour = rs.getString("arrivalHour");
            String arrivalMin = rs.getString("arrivalMin");
            String flightLength = rs.getString("length");
            String confirmation = "";
            confirmation += "Customer Information \n";
            confirmation += "--------------------------\n";
            confirmation += "Name: "+ firstName + " " + lastName + "\n";
            confirmation += "Address: " + streetAddress + ", " + city + ", " + state + ", " +
                    zipCode + "\n";
            confirmation += "Phone: " + countryCode + localCode + localNumber + "\n";
            confirmation += "Email: " + email + "\n\n";
            confirmation += "Flight Information \n";
            confirmation += "--------------------------\n";
            confirmation += "Airline: " + airlineName + "\n";
            confirmation += "Flight number: " + uniqueFlightNumber + "\n";
            confirmation += "Departing from: " + originCode + "\n";
            confirmation += "Destination: " + destinationCode + "\n";
            confirmation += "Date of Departure: " + departDate + "\n";
            confirmation += "Time of Departure: " + departHour + departMin + "\n";
            confirmation += "Arrival Time: " + arrivalHour + arrivalMin + "\n";
            confirmation += "Length of Flight: " + length;
            printWriter.println(confirmation);
            String insertBooking = "INSERT INTO Booking (bookingNumber, payerEmail,"
                    + " payerFirstName, payerLastName, bookingCityCode, bookingDate, numberOfPassengers) VALUES('"+bookingNumber+ " " +firstName+"', "
                    +lastName+", '"+bookingCityCode+"', '"+bookingDate+"', '"+numOfPassengers+"')";
            statement.executeUpdate(insertBooking);
            rs.close();
            statement.close();
            printWriter.close();
            conn.commit();
            conn.close();
            System.out.println("Congratulations! Below is your confirmation.");
            System.out.println();
            System.out.println(confirmation);
            System.out.println();
            System.out.println("Your confirmation has also been saved to the HW6.flight file");
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
}
}
