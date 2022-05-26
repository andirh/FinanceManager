package exceptions;

public class InvalidAccountException extends Throwable{

    public InvalidAccountException() {
        super("The account data is corrupted or does not exist");
    }
}
