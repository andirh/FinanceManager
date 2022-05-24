package exceptions;

public class NoStatementFoundException extends Throwable{

    public NoStatementFoundException() {
        super("No Statement could be found");
    }
}
