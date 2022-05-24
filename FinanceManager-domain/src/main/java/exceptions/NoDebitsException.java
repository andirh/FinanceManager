package exceptions;

public class NoDebitsException  extends Throwable{

    public NoDebitsException(){
        super("No payments could be found");
    }
}
