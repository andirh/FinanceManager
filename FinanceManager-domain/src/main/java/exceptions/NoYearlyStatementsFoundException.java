package exceptions;

public class NoYearlyStatementsFoundException extends Throwable{
    public NoYearlyStatementsFoundException() {
        super("No Yearly Statement Could be found");
    }
}
