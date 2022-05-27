package exceptions;

public class IllegalDateException extends Throwable{
    public IllegalDateException() {
        super("Wrong Date Input");
    }
}
