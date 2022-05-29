package transaction;

import exceptions.InvalidAmountException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class TransactionBuilder {

    String category;
    double amount;
    Date date;
    TransactionType type;


    public Transaction build() throws InvalidAmountException {
        //Default als ---?
        this.category = Objects.requireNonNullElse(category, "---");
            SimpleDateFormat DateFor = new SimpleDateFormat("yyyy/MM/dd");
        if (date == null){
            date = new Date();
        }
        String stringDate = DateFor.format(date);
        return new Transaction(category, amount, stringDate, type);
    }

    public TransactionBuilder category(String category){
        this.category = category;
        return this;
    }

    public TransactionBuilder amount(double amount){
        this.amount = amount;
        return this;
    }

    public TransactionBuilder date(String date){
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy/MM/dd");
        try {
            this.date = DateFor.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this;
    }

    public TransactionBuilder type(TransactionType type){
        this.type = type;
        return this;
    }

}
