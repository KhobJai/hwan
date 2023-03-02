package uk.intenso.hwan.shell;

public class ShellCommandException extends RuntimeException{


    public ShellCommandException(String message) {
        super(message);
    }

    public ShellCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
