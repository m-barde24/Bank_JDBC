package BankSystem;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;



public class BankServiceClass {
    public static void main(String[] args) {
        BankControllerClass bcc = new BankControllerClass();

        String dbUrl = "jdbc:postgresql://localhost:5432/Bank";
        String dbUser = "postgres";
        String dbPassword = "postgres";

        //Connection establishment to Bank's Database
        Connection connection = bcc.connect_To_DB(dbUrl, dbUser, dbPassword);

        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        String str = "y";
        char op;
        int accountNumber = 0;
        boolean validAccount = false;
        try {
             //Create table(....I have already created table....)
            //bcc.createTable(connection);

            System.out.println("Please Enter Customer Name : ");
            String customerName = sc.nextLine();
            if (!customerName.matches("[a-zA-Z\\s]+")) {
                throw new IllegalArgumentException("*** Customer name should contain only letters ***");
            }
            System.out.println("Please Enter Branch : ");
            String branch = sc.nextLine();
            System.out.println("Please Enter Account Type : ");
            String accountType = sc.nextLine();
            System.out.println("Please Enter Initial Balance : ");
            double balance = sc1.nextDouble();



            //create customer
            bcc.createCustomer(connection, customerName, branch, accountType, balance);
            bcc.displayAccountNumber(connection, customerName);


            System.out.println("Please Enter Account number to perform operations : ");
            accountNumber = sc1.nextInt();
            if (bcc.isAccountExists(connection, accountNumber)) {
                validAccount = true;
            } else {

                System.out.println("***Account number does not exist.(Please enter a valid account number.)***");
            }
            if (validAccount == true) {

                do {
                    System.out.println("*** Choose operation ***");
                    System.out.println("1. Check Balance");
                    System.out.println("2. Withdraw Money");
                    System.out.println("3. Find customer by its account type");
                    System.out.println("4. Deposit Money");
                    System.out.println("5. Change Branch");
                    System.out.println("6. Print All Customers");
                    System.out.println("7. Want to Delete Customer Details");
                    System.out.println("8. Exit");

                    int choice = sc1.nextInt();
                    switch (choice) {
                        case 1 -> {
                            bcc.checkBalance(connection, accountNumber);
                        }
                        case 2 -> {
                            System.out.println("Please Enter withdrawal Amount : ");
                            double withdrawAmount = sc1.nextDouble();
                            bcc.WithdrawMoney(connection, accountNumber, withdrawAmount);
                        }
                        case 3 -> {
                            //System.out.println("Please Enter your Account type : ");
                            //accountType = sc2.nextLine();
                            bcc.findCustomerType(connection, accountType);
                        }
                        case 4 -> {
                            System.out.println("Please Enter Deposit Amount : ");
                            double depositAmount = sc1.nextDouble();
                            bcc.depositMoney(connection, accountNumber, depositAmount);
                        }
                        case 5 -> {
                            System.out.println("Please Enter New Branch : ");
                            String newBranch = sc2.next();
                            bcc.changeBranch(connection, customerName, newBranch);
                        }
                        case 6 -> {
                            bcc.printAllCustomer(connection);
                        }
                        case 7 -> {
                            System.out.println("Please Enter Customer Account Number : ");
                            int accountNo = sc1.nextInt();
                            bcc.deleteCustomer(connection, accountNo);
                        }
                        case 8 -> {
                            System.exit(0);
                        }
                        default -> {
                            System.out.println("*** Wrong choice has been made ***");
                        }
                    }
                }while (Objects.equals(str, "y") || Objects.equals(str, "Y"));

                }

            } catch (IllegalArgumentException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}

