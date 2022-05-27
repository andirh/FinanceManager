package comparators;

import transaction.Transaction;

import java.util.Comparator;

public class StatementComparator implements Comparator<Transaction> {
    @Override
    public int compare(Transaction date1, Transaction date2) {
        String[] dateArray1 = date1.getDate().split("/");
        String[] dateArray2 = date2.getDate().split("/");
        for (int i = 0; i <= 2; i++) {
            if (Integer.parseInt(dateArray1[i]) > Integer.parseInt(dateArray2[i])) {
                return 1;
            } else if (Integer.parseInt(dateArray1[i]) > Integer.parseInt(dateArray2[i])) {
                return -1;
            }
        }
        return 0;

    }
}
