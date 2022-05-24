package statement;

import transaction.Transaction;

import java.util.List;

public class OverallStatement extends Statement{

    public OverallStatement(List<Transaction> transactions) {
        super(transactions);
    }

}
