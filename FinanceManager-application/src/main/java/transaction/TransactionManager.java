package transaction;

import exceptions.InvalidAmountException;

public class TransactionManager {

    TransactionBuilder builder = new TransactionBuilder();

    public Transaction createTransaction(String category, double amount, String date, TransactionType type) throws InvalidAmountException {
        return builder.date(date).category(category).amount(amount).type(type).build();
    }

    public Transaction createTransaction(String category, double amount, TransactionType type) throws InvalidAmountException {
        return builder.category(category).amount(amount).type(type).build();
    }

}
