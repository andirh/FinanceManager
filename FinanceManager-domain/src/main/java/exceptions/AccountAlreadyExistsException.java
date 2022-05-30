package exceptions;

public class AccountAlreadyExistsException extends Throwable{
    public AccountAlreadyExistsException() {
        super("There is already an account with this name");
    }
}
