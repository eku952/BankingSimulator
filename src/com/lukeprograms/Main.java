package com.lukeprograms;

import java.util.Scanner;

public class Main {
    private static String responceS;
    private static Account mainAccount;
    //private static boolean firstInit = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*if(firstInit) {
            Account.createAccount();
        }
*/
        System.out.println("Welcome to the bank! Do you have an account?");
        responceS = scanner.nextLine();

        if(responceS.toLowerCase().equals("yes")) {
            System.out.println("Please insert your username");
            String tempUsername = scanner.nextLine();
            System.out.println("Please insert your password");
            int tempPassword = scanner.nextInt();

            if(tempPassword == mainAccount.getPassword() && tempUsername == mainAccount.getUsername());
        }
        if(responceS.toLowerCase().equals("no")) {
            System.out.println("test");
            mainAccount = Account.createAccount();

        }
    }
}
