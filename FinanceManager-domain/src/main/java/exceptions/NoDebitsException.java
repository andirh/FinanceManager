package exceptions;

public class NoDebitsException  extends Throwable{

    public NoDebitsException(){
        super("No debits could be found");
    }
}
