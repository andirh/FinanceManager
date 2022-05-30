package exceptions;

public class NoOverallStatementFoundException extends Throwable {
    public NoOverallStatementFoundException() {
        super("No Overall Statement could be found");
    }
}
