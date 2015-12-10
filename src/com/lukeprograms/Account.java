package com.lukeprograms;

import java.util.Scanner;

public class Account {
    private String username;
    private int password;
    private int accountNumber = 0;
    private int balance;
    private double debt;
    private boolean loggedIn = false;

    StringBuilder accountBuffer = new StringBuilder();
    static Scanner scanner = new Scanner(System.in);

    public Account(String username, int password, int accountNumber, int balance, double debt) {
        this.username = username;
        this.password = password;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.debt = debt;
        accountNumber++;

        accountBuffer.append(username);
        accountBuffer.append(System.lineSeparator());
        accountBuffer.append(password);
        accountBuffer.append(System.lineSeparator());
        accountBuffer.append(accountNumber);
        accountBuffer.append(System.lineSeparator());
        accountBuffer.append(balance);
        accountBuffer.append(System.lineSeparator());
        accountBuffer.append(debt);
        FileHandling.writeFile(accountBuffer);
        System.out.println("Account Creation Complete!");
    }

    public void login(String username, int password) {
        System.out.println("Please Insert your Username:");
        this.username = scanner.nextLine();
        System.out.println("Please Insert your Password");
        this.password = scanner.nextInt();

        while(!loggedIn) {
            if(this.username == username && this.password == password) {
                System.out.println("Thank you " + username + " You have successfully logged in.");
                loggedIn = true;
            }
            else {
                System.out.println("Your Username or Password is Incorrect!");
            }
        }
    }

    public static Account createAccount() {
        //System.out.println("Welcome to the banking simulator, first you need to create an account!");
        System.out.println("Please insert a username");
        String tempUsername = scanner.nextLine();
        System.out.println("Please insert a password");
        int tempPassword = scanner.nextInt();
        System.out.println("Please insert your account balance");
        int tempBalance = scanner.nextInt();
        return new Account(tempUsername, tempPassword, 1, tempBalance, 0);
    }

    public static Account pullAccountFromSave() {
        String tempUsername = FileHandling.readFile(1);
        int tempPassword = Integer.parseInt(FileHandling.readFile(2));
        int tempBalance = Integer.parseInt(FileHandling.readFile(4));
        double tempDebt = Double.parseDouble(FileHandling.readFile(5));
        return new Account(tempUsername, tempPassword, 2, tempBalance, tempDebt);
    }

    public int withdraw(int withdrawAmount) {
        balance -= withdrawAmount;
        return balance;
    }

    public int deposit(int depositAmount) {
        balance += depositAmount;
        return balance;
    }

    public double overideDebt() {
        debt = Double.parseDouble(FileHandling.readFile(5));
        return debt;
    }

    public int getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public int getBalance() {
        return balance;
    }

    public double getDebt() {
        return debt;
    }
}
