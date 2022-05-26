package exceptions;

public class InvalidIdException extends Throwable{
    public InvalidIdException() {
        super("Id is invalid");
    }
}
