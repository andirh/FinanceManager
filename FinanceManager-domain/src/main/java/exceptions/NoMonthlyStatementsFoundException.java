package exceptions;

public class NoMonthlyStatementsFoundException extends Throwable{
    public NoMonthlyStatementsFoundException() {
        super("No Monthly Statements could be found");
    }
}
