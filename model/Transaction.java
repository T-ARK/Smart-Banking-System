package model;

import java.sql.Timestamp;

public class Transaction {
    private int transactionId;
    private int accountId;
    private double amount;
    private String type; // 'deposit' or 'withdrawal'
    private Timestamp transactionDate;

    public Transaction(int transactionId, int accountId, double amount, String type, Timestamp transactionDate) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.transactionDate = transactionDate;
    }

    // Getters
    public int getTransactionId() {
        return transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", accountId=" + accountId +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
