package main.domain.transaction;

import java.util.Date;

public class Transaction {

    private final String category;
    private final double amount;
    private final Date date;
    private final TransactionType type;


    public Transaction(String category, double amount, Date date, TransactionType type) {
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    public Transaction(String category, double amount, TransactionType type) {
        this.category = category;
        this.amount = amount;
        this.type = type;
        date = new Date();
    }
}
