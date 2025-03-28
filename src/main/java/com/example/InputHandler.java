package com.example;

import java.io.IOException;
import java.util.Scanner;

public class InputHandler {

    private final Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public int enterInt(int optionSize, boolean isBackAvailable, boolean isMenuAvailable) {
        int num = 0;
        while (true) {

            try {
                System.out.println("Please Enter a number");
                num = this.scanner.nextInt();
                if ((num >= 1 && num <= optionSize) || (isBackAvailable && num == 0)
                        || (isMenuAvailable && num == 99)) {
                    break;
                } else {

                    System.out.print("Select number from 1-" + Integer.toString(optionSize));
                    if (isBackAvailable) {
                        if(optionSize==0){
                            System.out.println("Select 0 to bo back");
                        }
                        else{System.out.print(" or 0 to go back");}
                        
                    }
                    if (isMenuAvailable) {
                        System.out.print(" or 99 to go to menu");

                    }
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.print("Invalid input. ");
                continue;
            }

        }

        return num;

    }

    public String getAlphaNumString() {
        String alphaNumString = "";
        while (true) {
            System.out.print("Enter username:\n >");
            alphaNumString = this.scanner.next();
            if (alphaNumString.matches("(?=.*[a-zA-Z])[a-zA-Z0-9]+") || alphaNumString.equals("-1")) {
                break;
            } else {
                System.out.println(
                        "Invalid input.\nPlease enter a string containing only alphanumeric characters and at least one or go back with -1");
           }

        }
        return alphaNumString;

    }

    public String getLetterOrHint() {
        String letterOrHint = "";
        System.out.print("\n>");
        letterOrHint = this.scanner.next();
        return letterOrHint;
    

}

    public String getStringInt() {
        String stringInt = "";
        System.out.println("Please Enter a number");
        stringInt = this.scanner.next();

        return stringInt;
        
    }



    public void waitForAnyKey() {
    System.out.println("\nPress any key to continue...");
    try {
        System.in.read(); // Reads a single character
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}