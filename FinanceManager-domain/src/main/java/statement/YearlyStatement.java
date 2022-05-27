package statement;

import exceptions.InvalidIdException;
import transaction.Transaction;

import java.time.Year;
import java.util.List;

public class YearlyStatement extends Statement {

    private final Year year;
    private final String statementId;

    //TODO maybe direct set?
    public YearlyStatement(int year, List<Transaction> transactions, long accountId) throws InvalidIdException {
        super(transactions, accountId);
        this.year = Year.of(year);
        this.statementId = year + "-" + accountId;
    }

    @Override
    public String toString() {
        return super.getAccountId() + "," + statementId +  "," + year.getValue();
    }

    @Override
    public String getStatementId() {
        return statementId;
    }


}
