package statement;

import comparators.StatementComparator;
import exceptions.InvalidIdException;
import exceptions.InvalidStatementException;
import exceptions.NoDebitsException;
import exceptions.NoPaymentsException;
import transaction.Transaction;

import java.util.*;

public abstract class Statement {

    private final SortedSet<Transaction> transactions;
    //TODO: Summe beim lesen direkt berechnen
    private double sum;
    private long id;

    public Statement(List<Transaction> transactions, long id) throws InvalidIdException {
        if(!(id > 1000000000L && id < 9999999999L)){
            throw new InvalidIdException();
        }
        this.id = id;
        this.transactions = new TreeSet<>(new StatementComparator());
        this.transactions.addAll(transactions);
    }

    public SortedSet<Transaction> getAllTransactions() throws InvalidStatementException {
        if(transactions.size() == 0){
            throw new InvalidStatementException();
        }
        return transactions;
    }

    public SortedSet<Transaction> getAllPayments() throws InvalidStatementException, NoPaymentsException {
        if(transactions.size() == 0){
            throw new InvalidStatementException();
        }
        SortedSet<Transaction> payments = new TreeSet<>(new StatementComparator());
        transactions.forEach(transaction -> {
            if(transaction.getType().isPayment()){
                payments.add(transaction);
            }
        });
        if (payments.size() == 0){
            throw new NoPaymentsException();
        } else {
            return payments;
        }
    }

    public SortedSet<Transaction> getAllDebits() throws InvalidStatementException, NoDebitsException {
        if(transactions.size() == 0){
            throw new InvalidStatementException();
        }
        SortedSet<Transaction> debits = new TreeSet<>(new StatementComparator());
        transactions.forEach(transaction -> {
            if(transaction.getType().isDebit()){
                debits.add(transaction);
            }
        });
        if (debits.size() == 0){
            throw new NoDebitsException();
        } else {
            return debits;
        }
    }

    public double getSum(){
        double sum = 0.0;
        for (Transaction transaction: transactions) {
            sum += transaction.getAmount();
        }
        return sum;
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public long getId() {
        return id;
    }
}
