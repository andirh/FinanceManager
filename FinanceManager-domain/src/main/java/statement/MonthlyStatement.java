package statement;

import transaction.Transaction;

import java.time.Month;
import java.time.Year;
import java.util.List;

public class MonthlyStatement extends Statement{

    private final Year year;
    private final Month month;

    //TODO maybe direct set?
    public MonthlyStatement(int year, int month, List<Transaction> transactions) {
        super(transactions);
        this.year = Year.of(year);;
        this.month = Month.of(month);
    }

}
