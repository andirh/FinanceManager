package comparators;

import transaction.Transaction;

import java.util.Comparator;

public class StatementComparator implements Comparator<Transaction> {
    @Override
    public int compare(Transaction o1, Transaction o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}
