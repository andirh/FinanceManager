package statement;

import comparators.StatementComparator;
import exceptions.*;
import transaction.Transaction;
import java.util.*;

public abstract class Statement {

    private final SortedSet<Transaction> transactions;
    private final long accountId;

    public abstract String toString();

    public abstract String getStatementId();

    public Statement(List<Transaction> transactions, long accountId) throws InvalidIdException {
        if (!(accountId > 1000000000L && accountId < 9999999999L)) {
            throw new InvalidIdException();
        }
        this.accountId = accountId;
        this.transactions = new TreeSet<>(new StatementComparator());
        this.transactions.addAll(transactions);
    }

    public SortedSet<Transaction> getAllTransactions() throws InvalidStatementException {
        if (transactions.size() == 0) {
            throw new InvalidStatementException();
        }
        return transactions;
    }

    public SortedSet<Transaction> getAllPayments() throws InvalidStatementException, NoPaymentsException {
        if (transactions.size() == 0) {
            throw new InvalidStatementException();
        }
        SortedSet<Transaction> payments = extractTransactionsByType(0);
        if (payments.size() == 0) {
            throw new NoPaymentsException();
        } else {
            return payments;
        }
    }

    public SortedSet<Transaction> getAllDebits() throws InvalidStatementException, NoDebitsException {
        if (transactions.size() == 0) {
            throw new InvalidStatementException();
        }
        SortedSet<Transaction> debits = extractTransactionsByType(1);
        if (debits.size() == 0) {
            throw new NoDebitsException();
        } else {
            return debits;
        }
    }

    private SortedSet<Transaction> extractTransactionsByType(int transactionType) {
        SortedSet<Transaction> transactionsByType = new TreeSet<>(new StatementComparator());
        if (transactionType == 1) {
            transactions.forEach(transaction -> {
                if (transaction.getType().isDebit()) {
                    transactionsByType.add(transaction);
                }
            });
        } else {
            transactions.forEach(transaction -> {
                if (transaction.getType().isPayment()) {
                    transactionsByType.add(transaction);
                }
            });
        }
        return transactionsByType;
    }


    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public long getAccountId() {
        return accountId;
    }


}
