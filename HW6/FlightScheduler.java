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
            zipCode, phone, email, passFirst, passLast, passStreetAdress,
            passCity, passState, passZipCode, passPhone, passEmail, airlineName,
            airlineCode, airlineCountry, departureDate, arrivalDate,
            airportName, airportCity, airportState, bookingDate;

    public static void main(String[] args) {
        UI();

    }

    /*private static void openConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    } */
    private static void UI() {
        try {
            PrintWriter outFile = new PrintWriter("HW6Error.txt");
            System.out.println("---------------------------");
            System.out.println(" Flight Reservation System ");
            System.out.println("---------------------------");
            System.out.print("First name: ");
            firstName = USERINPUT.nextLine();
            System.out.print("Last name: ");
            lastName = USERINPUT.nextLine();
            System.out.print("Street address "
                    + "(do not include city, state, or zip): ");
            streetAddress = USERINPUT.nextLine();
            System.out.print("City: ");
            city = USERINPUT.nextLine();
            System.out.print("State Abbreviation: ");
            state = USERINPUT.nextLine();
            System.out.print("5 digit zip code: ");
            zipCode = USERINPUT.nextLine();
            System.out.print("Email address:");
            email = USERINPUT.nextLine();

            if (!(isAlpha(firstName))) {
                outFile.println(firstName + " is not a valid name. "
                        + "a-z, A-Z only.");
            }
            if (!(isAlpha(lastName))) {
                outFile.println(lastName + " is not a valid name. "
                        + "a-z, A-Z only.");
            }
            if (!(isAlpha(city))) {
                outFile.println(city + " is not a valid city. a-z, A-Z only.");
            }
            if (!(isAlpha(state))) {
                outFile.println(state + " is not a valid state. "
                        + "a-z, A-Z only.");
            }
            if (!(phone.matches(REGEXSTR))) {
                outFile.println(phone + " is not a valid "
                        + "phone number. 9999999999, 1-999-999-9999 and "
                        + "999-999-9999 only.");
            }
            if (!(email.contains("@"))) {
                outFile.println(email + " is not a valid email. "
                        + "It must contain an @ character.");
            }

            outFile.close();

        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
    }

    public static boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }
}
