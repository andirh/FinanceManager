package statement;

import exceptions.InvalidIdException;
import transaction.Transaction;

import java.time.Month;
import java.time.Year;
import java.util.List;

public class MonthlyStatement extends Statement {

    private final Year year;
    private final Month month;
    private final String statementId;

    public MonthlyStatement(int year, int month, List<Transaction> transactions, long accountId) throws InvalidIdException {
        super(transactions, accountId);
        this.year = Year.of(year);
        this.month = Month.of(month);
        this.statementId = year + "-" + month + "-" + accountId;
    }

    @Override
    public String toString() {
        return super.getAccountId() + "," + statementId + ","  + year.getValue() + "," + month.getValue();
    }

    @Override
    public String getStatementId() {
        return statementId;
    }

}
