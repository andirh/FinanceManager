package exceptions;

public class InvalidTransactionTypeException extends Throwable{

    public InvalidTransactionTypeException(){
        super("Invalid Transaction configuration");
    }
}
