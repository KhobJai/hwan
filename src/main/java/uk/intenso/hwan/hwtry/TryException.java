package uk.intenso.hwan.hwtry;

public class TryException extends RuntimeException{


    public TryException(String message) {
        super(message);
    }

    public TryException(String message, Throwable cause) {
        super(message, cause);
    }


}
