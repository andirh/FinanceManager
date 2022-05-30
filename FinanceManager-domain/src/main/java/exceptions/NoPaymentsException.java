package exceptions;

public class NoPaymentsException extends Throwable{

    public NoPaymentsException(){
        super("No payments could be found");
    }
}
