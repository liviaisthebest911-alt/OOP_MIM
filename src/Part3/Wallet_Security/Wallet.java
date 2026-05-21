package Part3.Wallet_Security;

import java.util.ArrayList;

public class Wallet {
    private double balance;
    private String ownerName;
    private ArrayList<String> transactionHistory;

    public Wallet(double balance, String ownerName) {
        if (balance < 0) {
            throw new IllegalArgumentException("So du phai lon hon hoac bang 0");
        }
        this.balance = balance;
        this.ownerName = ownerName;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: +" + amount);
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: -" + amount);
            return true;
        }
        transactionHistory.add("Failed withdrawal: " + amount);
        return false;
    }
}