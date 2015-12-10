package com.lukeprograms;



import java.util.DoubleSummaryStatistics;
import java.util.Scanner;

public class Bank {
    private static int currentLoans = 0;
    private static double interest = 0.08;

    static Scanner scanner = new Scanner(System.in);

    public static int takeLoan(){
        System.out.println("Hello, please insert how much money you would like to loan");
        int response = scanner.nextDouble();
        double currentDebt = Double.parseDouble(FileHandling.readFile(5));
        StringBuilder previousInfo = new StringBuilder();
        double interestRate = response * interest;
        double newDebt = currentDebt + (interestRate + response);
        int newBalance = Integer.parseInt(FileHandling.readFile(4));
        newBalance += response;

        previousInfo.append(FileHandling.readFile(1));
        previousInfo.append(System.lineSeparator());
        previousInfo.append(FileHandling.readFile(2));
        previousInfo.append(System.lineSeparator());
        previousInfo.append(FileHandling.readFile(3));
        previousInfo.append(System.lineSeparator());
        previousInfo.append(newBalance);
        previousInfo.append(System.lineSeparator());
        previousInfo.append(newDebt);

        FileHandling.writeFile(previousInfo);
        System.out.println("Ok, you have received a " + response + " dollar loan");
        return response;
    }

    public static double payLoan() {
        System.out.println("Please insert the amount of money you wish to pay back. Your current debt is: " + FileHandling.readFile(5));
        double response = scanner.nextDouble();
        double currentDebt = Double.parseDouble(FileHandling.readFile(5));
        double newDebt = response - currentDebt;
        StringBuilder previousInfo = new StringBuilder();

        previousInfo.append(FileHandling.readFile(1));
        previousInfo.append(System.lineSeparator());
        previousInfo.append(FileHandling.readFile(2));
        previousInfo.append(System.lineSeparator());
        previousInfo.append(FileHandling.readFile(3));
        previousInfo.append(System.lineSeparator());
        previousInfo.append(newDebt);
        previousInfo.append(System.lineSeparator());
        previousInfo.append(newDebt);

        FileHandling.writeFile(previousInfo);
        System.out.println("You have removed " + response + " dollars, your current debt is:" + currentDebt);
        return response;
    }
}
