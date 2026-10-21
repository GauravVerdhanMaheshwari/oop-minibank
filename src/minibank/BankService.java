package minibank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private Map<String, Account> accounts = new HashMap<>();

    public Account openAccount(String name, String email, String mobile, String line, String city, String pincode, long initialDeposit) {
        Customer.Address address = new Customer.Address(line, city, pincode);
        Customer customer = new Customer(name, email, mobile, address);
        Account account = new Account(customer, initialDeposit);
        accounts.put(account.getAccountNumber(), account);
        return account;
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public boolean deposit(String accountNumber, long amount) {
        Account account = getAccount(accountNumber);
        if (account != null && amount > 0) {
            account.deposit(amount);
            return true;
        }
        return false;
    }

    public boolean withdraw(String accountNumber, long amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            return account.withdraw(amount);
        }
        return false;
    }

    public boolean transfer(String fromAccountNum, String toAccountNum, long amount) {
        Account fromAcc = getAccount(fromAccountNum);
        Account toAcc = getAccount(toAccountNum);
        if (fromAcc != null && toAcc != null && amount > 0) {
            if (fromAcc.withdraw(amount)) {
                toAcc.deposit(amount);
                return true;
            }
        }
        return false;
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }
}
