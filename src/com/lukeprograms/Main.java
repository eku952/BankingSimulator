package com.lukeprograms;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.Console;
import java.util.Scanner;

public class Main {
    private static String responceS;
    private static Account mainAccount;
    private static boolean login = false;
    private static boolean startup = true;
    public static int currentSaveFile;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*if(firstInit) {
            Account.createAccount();
        }
*/
        //System.out.println("What save file are you pulling from? If this is the first time you are starting this program, simply put '1' in as your answer");
        //currentSaveFile = scanner.nextInt();
        //FileHandling.setSaveFile(currentSaveFile);

        System.out.println("Welcome to the bank! Do you have an account?");
        responceS = scanner.next();

        while(startup) {
            if (responceS.toLowerCase().equals("yes")) {
                System.out.println("Please insert your username");
                String tempUsername = scanner.next();
                System.out.println("Please insert your password (numbers only)");
                int tempPassword = scanner.nextInt();

                String saveUsername = FileHandling.readFile(1);
                String savePassword = FileHandling.readFile(2);

                if (saveUsername.equals(tempUsername) && savePassword.equals(Integer.toString(tempPassword))) {
                    mainAccount = Account.pullAccountFromSave(currentSaveFile);
                    System.out.println("Login Successful");
                    startup = false;
                    login = true;
                } else {
                    System.out.println("Incorrect Username or Password");
                }
            }

            if(responceS.toLowerCase().equals("no")) {
                //System.out.println("test");
                startup = false;
                login = true;
                mainAccount = Account.createAccount();
            }
        }

        while(login) {
            System.out.println("Would you like to withdraw, deposit, check balance, loans, or exit?");
            responceS = scanner.nextLine();

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(responceS.toLowerCase().equals("withdraw")) {
                System.out.println("Please insert the amount you wish to withdraw.");
                int withdraw = scanner.nextInt();

                if(withdraw <= mainAccount.getBalance()) {
                    mainAccount.withdraw(withdraw);
                    //String overwrite = FileHandling.readFullFile();
                    String newBalance = String.valueOf(mainAccount.getBalance());
                    FileHandling.overwriteFile(newBalance);
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
                String newBalance = String.valueOf(mainAccount.getBalance());
                FileHandling.overwriteFile(newBalance);
                System.out.println("Deposit successful, you deposited " + deposit + " dollars into your account, balance is " + mainAccount.getBalance());
            }

            if(responceS.toLowerCase().equals("check balance")) {
                System.out.println("Your account balance is: " + mainAccount.getBalance());
            }

            if(responceS.toLowerCase().equals("loans")) {
                System.out.println("Would you like to take out a loan, pay back a previous loan, or check your debt?");
                responceS = scanner.nextLine();

                if(responceS.toLowerCase().equals("take loan")) {
                    mainAccount.deposit(Bank.takeLoan());
                    System.out.println("You have successfully taken out a loan, your new balance is " + mainAccount.getBalance() + " and your dept is " + mainAccount.getDebt());
                }

                if(responceS.toLowerCase().equals("pay back")) {
                    mainAccount.withdraw((int) Bank.payLoan());
                    mainAccount.overrideDebt();
                }

                if(responceS.toLowerCase().equals("check debt")) {
                    System.out.println("Your debt is currently: " + mainAccount.getDebt() + " dollars.");
                }
            }

            if(responceS.toLowerCase().equals("exit")) {
                System.out.println("Thank you for your visit at the bank!");
                login = false;
            }

            if(responceS.toUpperCase().equals("JSON")) {
                JSONObject json = null;
                try {
                    json = FileHandling.readAsJSON();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                JSONObject firstAccount = (JSONObject) json.get("01654");
                JSONParser parser = new JSONParser();

                System.out.println("JSON Confirmed.");
                responceS = scanner.nextLine();

                switch(responceS) {
                    case "read":

                        System.out.println(firstAccount);
                        System.out.println(firstAccount.get("balance"));
                        System.out.println(firstAccount.get("pin"));
                        break;

                    case "deposit":
                        System.out.println("Please insert the amount you wish to deposit");
                        double deposit = scanner.nextDouble();

                        double currentBalance = (Double)firstAccount.get("balance");
                        double newBalance = currentBalance + deposit;
                        System.out.println(newBalance);

                        firstAccount.put("balance", newBalance);

                        //currently not working--->
                        //FileHandling.writeAsJSON(newBalance, "balance");

                        break;
                }
            }

            if(responceS.toLowerCase().equals("reset")) {
                StringBuilder reset = new StringBuilder();

                reset.append("test");
                reset.append(System.lineSeparator());
                reset.append("123");
                reset.append(System.lineSeparator());
                reset.append("1");
                reset.append(System.lineSeparator());
                reset.append("50");
                reset.append(System.lineSeparator());
                reset.append("0.0");
                FileHandling.writeFile(reset);
                System.out.println("Reset Complete.");
                login = false;
            }
        }
    }
}
