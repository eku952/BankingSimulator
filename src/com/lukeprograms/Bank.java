package com.lukeprograms;



import java.util.DoubleSummaryStatistics;
import java.util.Scanner;

public class Bank {
    private static int currentLoans = 0;
    private static double interest = 0.08;

    static Scanner scanner = new Scanner(System.in);

    public static int takeLoan(){
        System.out.println("Hello, please insert how much money you would like to loan");
        int responce = scanner.nextInt();
        double currentDebt = Double.parseDouble(FileHandling.readFile(5));
        StringBuilder previousInfo = new StringBuilder();
        double interestRate = responce * interest;
        double newDebt = currentDebt + (interestRate + responce);
        int newBalance = Integer.parseInt(FileHandling.readFile(4));
        newBalance += responce;

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
        System.out.println("Ok, you have received a " + responce + " dollar loan");
        return responce;
    }
}
