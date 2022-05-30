package exceptions;

public class NoAccountFilesException extends Throwable{
    public NoAccountFilesException() {
        super("No account files could be found");
    }
}
