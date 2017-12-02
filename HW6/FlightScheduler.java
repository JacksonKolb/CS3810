package flightquery;

import java.util.*;
import java.io.*;

public class FlightScheduler {

    public static final Scanner USERINPUT = new Scanner(System.in);
    public static final String REGEXSTR = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?"
            + "[0-9]{4}$";

    public static void main(String[] args) {
        try {
            PrintWriter outFile = new PrintWriter("HW6Error.txt");
            

            //user information
            String name,
                   streetAddress,
                   city,
                   state,
                   zipCode,
                   phone,
                   email;

            System.out.println("Enter your name: ");
            if (isAlpha(USERINPUT.nextLine())) {
                name = USERINPUT.nextLine();
            } else {
                outFile.println("user provided name is not a valid name. "
                        + "a-z, A-Z only.");
            }
            System.out.println("Enter your street address (just the street "
                    + "name and number: ");
            streetAddress = USERINPUT.nextLine();
            System.out.println("Enter your city: ");
            if (isAlpha(USERINPUT.nextLine())) {
                city = USERINPUT.nextLine();
            } else {
                outFile.println("user provided city is not a valid city. "
                        + "a-z, A-Z only.");
            }
            System.out.println("Enter your state: ");
            if (isAlpha(USERINPUT.nextLine())) {
                state = USERINPUT.nextLine();
            } else {
                outFile.println("user provided state is not a valid state. "
                        + "a-z, A-Z only.");
            }
            System.out.println("Enter your phone: ");
            if (USERINPUT.nextLine().matches(REGEXSTR)) {
                phone = USERINPUT.nextLine();
                // if matches 9999999999, 1-999-999-9999 and 999-999-9999
            } else {
                outFile.println("user provided phone number is not a valid "
                        + "phone number. 9999999999, 1-999-999-9999 and "
                        + "999-999-9999 only.");
            }
            System.out.println("Enter your email: ");
            if ((USERINPUT.nextLine().contains("@"))) {
                email = USERINPUT.nextLine();
            } else {
                outFile.println("user provided email is not a valid email. "
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

