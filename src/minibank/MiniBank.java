package minibank;

import java.util.Scanner;

public class MiniBank {
    public static void main(String[] args) {
        BankInfo bankInfo = new BankInfo("CHARUSAT MiniBank", "FTE Campus Branch");
        BankService bankService = new BankService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println(" Welcome to " + bankInfo.name() + " (" + bankInfo.branch() + ")");
        System.out.println("==================================================");

        boolean running = true;

        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. View Account Details");
            System.out.println("6. Exit");
            System.out.print("Enter option (1-6): ");

            int optionNum;
            try {
                optionNum = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                continue;
            }

            MenuOption option = switch (optionNum) {
                case 1 -> MenuOption.OPEN_ACCOUNT;
                case 2 -> MenuOption.DEPOSIT;
                case 3 -> MenuOption.WITHDRAW;
                case 4 -> MenuOption.TRANSFER;
                case 5 -> MenuOption.VIEW_DETAILS;
                case 6 -> MenuOption.EXIT;
                default -> null;
            };

            if (option == null) {
                System.out.println("Invalid menu option. Please try again.");
                continue;
            }

            switch (option) {
                case OPEN_ACCOUNT -> {
                    System.out.print("Enter Customer Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Mobile Number: ");
                    String mobile = scanner.nextLine();
                    System.out.print("Enter Address Line: ");
                    String line = scanner.nextLine();
                    System.out.print("Enter City: ");
                    String city = scanner.nextLine();
                    System.out.print("Enter Pincode: ");
                    String pincode = scanner.nextLine();
                    System.out.print("Enter Initial Deposit Amount: ");
                    long initBalance = Long.parseLong(scanner.nextLine().trim());

                    Account newAcc = bankService.openAccount(name, email, mobile, line, city, pincode, initBalance);
                    System.out.println("\n[SUCCESS] Account created successfully!");
                    System.out.println("Account Number: " + newAcc.getAccountNumber());
                    System.out.println("Customer ID: " + newAcc.getCustomer().getCustomerId());
                }
                case DEPOSIT -> {
                    System.out.print("Enter Account Number: ");
                    String accNum = scanner.nextLine();
                    System.out.print("Enter Deposit Amount: ");
                    long amount = Long.parseLong(scanner.nextLine().trim());

                    if (bankService.deposit(accNum, amount)) {
                        System.out.println("[SUCCESS] Deposited ₹" + amount + ". New Balance: ₹" + bankService.getAccount(accNum).getBalance());
                    } else {
                        System.out.println("[ERROR] Account not found or invalid amount.");
                    }
                }
                case WITHDRAW -> {
                    System.out.print("Enter Account Number: ");
                    String accNum = scanner.nextLine();
                    System.out.print("Enter Withdrawal Amount: ");
                    long amount = Long.parseLong(scanner.nextLine().trim());

                    if (bankService.withdraw(accNum, amount)) {
                        System.out.println("[SUCCESS] Withdrew ₹" + amount + ". Remaining Balance: ₹" + bankService.getAccount(accNum).getBalance());
                    } else {
                        System.out.println("[ERROR] Insufficient funds or account not found.");
                    }
                }
                case TRANSFER -> {
                    System.out.print("Enter Sender Account Number: ");
                    String fromAcc = scanner.nextLine();
                    System.out.print("Enter Recipient Account Number: ");
                    String toAcc = scanner.nextLine();
                    System.out.print("Enter Transfer Amount: ");
                    long amount = Long.parseLong(scanner.nextLine().trim());

                    if (bankService.transfer(fromAcc, toAcc, amount)) {
                        System.out.println("[SUCCESS] Transferred ₹" + amount + " from " + fromAcc + " to " + toAcc);
                    } else {
                        System.out.println("[ERROR] Transfer failed! Check account numbers and available balance.");
                    }
                }
                case VIEW_DETAILS -> {
                    System.out.print("Enter Account Number: ");
                    String accNum = scanner.nextLine();
                    Account account = bankService.getAccount(accNum);
                    if (account != null) {
                        System.out.println("\n--- Account Details ---");
                        System.out.println(account);
                        System.out.println(account.getCustomer());
                    } else {
                        System.out.println("[ERROR] Account not found.");
                    }
                }
                case EXIT -> {
                    System.out.println("\nThank you for using CHARUSAT MiniBank. Goodbye!");
                    running = false;
                }
            }
        }
        scanner.close();
    }
}
