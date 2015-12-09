package com.lukeprograms;

import java.util.Scanner;

public class Main {
    private static String responceS;
    private static Account mainAccount;
    private static boolean login = false;
    private static boolean startup = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*if(firstInit) {
            Account.createAccount();
        }
*/
        System.out.println("Welcome to the bank! Do you have an account?");
        responceS = scanner.nextLine();

        while(startup) {
            if (responceS.toLowerCase().equals("yes")) {
                System.out.println("Please insert your username");
                String tempUsername = scanner.nextLine();
                System.out.println("Please insert your password");
                int tempPassword = scanner.nextInt();

                String saveUsername = FileHandling.readFile(1);
                String savePassword = FileHandling.readFile(2);

                if (saveUsername.equals(tempUsername) && savePassword.equals(Integer.toString(tempPassword))) {
                    mainAccount = Account.pullAccountFromSave();
                    System.out.println("Login Successful");
                    startup = false;
                    login = true;
                } else {
                    System.out.println("Incorrect Username or Password");
                }
            }
        }
        if(responceS.toLowerCase().equals("no")) {
            //System.out.println("test");
            mainAccount = Account.createAccount();
            login = true;
        }

        while(login) {
            System.out.println("Would you like to withdraw, deposit, check balance, or exit?");
            responceS = scanner.nextLine();

            if(responceS.toLowerCase().equals("withdraw")) {
                System.out.println("Please insert the amount you wish to withdraw.");
                int withdraw = scanner.nextInt();

                if(withdraw <= mainAccount.getBalance()) {
                    mainAccount.withdraw(withdraw);
                    System.out.println("Withdraw successful, you withdrew " + withdraw + " dollars from your account balance is " + mainAccount.getBalance());
                }
                else {
                    System.out.println("You cannot withdraw more than what you have stored: " + mainAccount.getBalance());
                }
            }

            if(responceS.toLowerCase().equals("deposit")) {
                System.out.println("Please insert the amount you wish to deposit.");
                int deposit = scanner.nextInt();

                mainAccount.deposit(deposit);
                System.out.println("Deposit successful, you deposited " + deposit + " dollars into your account, balance is " + mainAccount.getBalance());
            }

            if(responceS.toLowerCase().equals("check balance")) {
                System.out.println("Your account balance is: " + mainAccount.getBalance());
            }

            if(responceS.toLowerCase().equals("exit")) {
                System.out.println("Thank you for your visit at the bank!");
                login = false;
            }
        }
    }
}
