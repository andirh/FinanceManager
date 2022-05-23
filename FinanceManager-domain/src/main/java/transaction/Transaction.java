package transaction;

import java.util.Date;
import java.util.Objects;

public class Transaction {

    final String category;
    final double amount;
    final Date date;
    final TransactionType type;


    public Transaction(String category, double amount, Date date, TransactionType type) {
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.type = type;
        if(category == null)
            category = "";
    }

    public Transaction(String category, double amount, TransactionType type) {
        this.category = Objects.requireNonNullElse(category, "");
        this.amount = amount;
        this.type = type;
        date = new Date();
        if (this.amount == 0.0)
            //throw new IllegalAmountException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 && category.equals(that.category) && date.equals(that.date) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, amount, date, type);
    }
}
