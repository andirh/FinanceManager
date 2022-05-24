package exceptions;

public class AccountNotFoundException extends Throwable{

    public AccountNotFoundException() {
        super("No account could be found");
    }
}
