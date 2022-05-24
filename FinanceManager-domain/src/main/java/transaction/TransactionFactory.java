package transaction;

import exceptions.InvalidAmountException;

import java.util.Date;
import java.util.Objects;

public class TransactionFactory {

    String category;
    double amount;
    Date date;
    TransactionType type;


    public Transaction build() throws InvalidAmountException {
        this.category = Objects.requireNonNullElse(category, "---");
        if (date == null){
            date = new Date();
        }
        return new Transaction(category, amount, date, type);
    }

    public TransactionFactory category(String category){
        this.category = category;
        return this;
    }

    public TransactionFactory amount(double amount){
        this.amount = amount;
        return this;
    }

    public TransactionFactory listNumber(Date date){
        this.date = date;
        return this;
    }

    public TransactionFactory type(TransactionType type){
        this.type = type;
        return this;
    }

}
