package statement;

import account.Account;
import account.AccountBuilder;
import account.Owner;
import exceptions.IllegalDateException;
import exceptions.InvalidIdException;
import transaction.Transaction;

import java.time.Year;
import java.util.List;

public class StatementBuilder {

    private List<Transaction> transactions;
    private long accountId;
    private int year;
    private int month;

    public Statement build() throws InvalidIdException, IllegalDateException {
        if (year > Year.now().getValue() || month < 0 || month > 12) {
            throw new IllegalDateException();
        }
        Statement statement;
        if (year == 0 && month == 0) {
            statement = new OverallStatement(transactions, accountId);
        } else if (month == 0) {
            statement = new YearlyStatement(year, transactions, accountId);
        } else {
            statement = new MonthlyStatement(year, month, transactions, accountId);
        }
        year = 0;
        month = 0;
        return statement;
    }

    public StatementBuilder transactions(List<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }

    public StatementBuilder accountId(long accountId) {
        this.accountId = accountId;
        return this;
    }

    public StatementBuilder year(int year) {
        this.year = year;
        return this;
    }

    public StatementBuilder month(int month) {
        this.month = month;
        return this;
    }

}
