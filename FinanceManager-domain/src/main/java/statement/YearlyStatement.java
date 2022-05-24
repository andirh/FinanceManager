package statement;

import transaction.Transaction;

import java.time.Year;
import java.util.List;

public class YearlyStatement extends Statement{

    private final Year year;

    //TODO maybe direct set?
    public YearlyStatement(int year, List<Transaction> transactions) {
        super(transactions);
        this.year = Year.of(year);;
    }
}
