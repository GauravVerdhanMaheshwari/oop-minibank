package minibank;

import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private static long accountCounter = 0;
    private final String accountNumber;
    private Customer customer;
    private long balance;
    private boolean active;

    public Account(Customer customer, long openingBalance) {
        this.accountNumber = generateAccountNumber();
        this.customer = customer;
        this.balance = openingBalance >= 0 ? openingBalance : 0;
        this.active = true;
    }

    public Account(Customer customer) {
        this(customer, 0);
    }

    private static synchronized String generateAccountNumber() {
        return String.format("AC%04d", ++accountCounter);
    }

    public void deposit(long amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(long amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public String getAccountNumber() { return accountNumber; }
    public Customer getCustomer() { return customer; }
    public long getBalance() { return balance; }
    public boolean isActive() { return active; }

    @Override
    public String toString() {
        return accountNumber + " | Owner: " + customer.getName() + " | Balance: ₹" + balance + " | Active: " + active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}
