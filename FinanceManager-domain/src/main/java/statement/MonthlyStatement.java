package statement;

import exceptions.InvalidIdException;
import transaction.Transaction;

import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Objects;

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

    public Year getYear() {
        return year;
    }

    public Month getMonth() {
        return month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthlyStatement that = (MonthlyStatement) o;
        return statementId.equals(that.statementId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statementId);
    }
}
