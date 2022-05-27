package exceptions;

public class StatementAlreadyExistsException extends Throwable{

    public StatementAlreadyExistsException() {
        super("statement already exists");
    }
}
