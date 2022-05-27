package statement;

import exceptions.InvalidIdException;
import transaction.Transaction;

import java.util.List;
import java.util.Objects;

public class OverallStatement extends Statement{

    private final String statementId;

    @Override
    public String toString() {
        return super.getAccountId() + "," + statementId;
    }

    public OverallStatement(List<Transaction> transactions, long accountId) throws InvalidIdException {
        super(transactions, accountId);
        this.statementId = "O-" + accountId;
    }

    @Override
    public String getStatementId() {
        return statementId;
    }

}
