package transaction;

import exceptions.InvalidAmountException;

import java.util.Objects;

public class Transaction {

    final String category;
    final double amount;
    final String date;
    final TransactionType type;


    public Transaction(String category, double amount, String date, TransactionType type) throws InvalidAmountException {
        this.category = Objects.requireNonNullElse(category, "---");
        this.amount = amount;
        this.type = type;
        this.date = date;
        if (this.amount <= 0.0) {
            throw new InvalidAmountException();
        }
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

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public TransactionType getType() {
        return type;
    }
}
