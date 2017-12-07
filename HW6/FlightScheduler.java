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
            country, phone, email, passFirst, passLast, passStreetAddress,
            passCity, passState, passCountry, passPhone, passEmail, airlineName,
            airlineCode, airlineCountry, departureDate, arrivalDate,
            airportName, airportCity, airportState, bookingDate, username, password;
    private static int zipCode, countryCode, localCode, localNumber,
            bookingNumber, uniqueFlightNumber, passID, passZipCode,
            originCode, destinationCode, length, departureHour, departureMinute,
            arrivalHour, arrivalMin, airportCode, bookingCityCode,
            numOfPassengers;

    public static void main(String[] args) {
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
            if (!(phone.matches(REGEXSTR))) {
                outFile.println(phone + " is not a valid "
                        + "phone number. 9999999999, 1-999-999-9999 and "
                        + "999-999-9999 only.");
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
                passFirst = USERINPUT.nextLine();
                System.out.print("Passenger Last Name: ");
                passLast = USERINPUT.nextLine();
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
                System.out.print("Phone Number: ");
                passPhone = USERINPUT.nextLine();
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
}
