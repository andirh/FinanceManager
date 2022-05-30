package statement;

import exceptions.InvalidIdException;
import transaction.Transaction;

import java.time.Year;
import java.util.List;
import java.util.Objects;

public class YearlyStatement extends Statement {

    private final Year year;
    private final String statementId;

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

    public Year getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YearlyStatement that = (YearlyStatement) o;
        return statementId.equals(that.statementId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statementId);
    }
}
