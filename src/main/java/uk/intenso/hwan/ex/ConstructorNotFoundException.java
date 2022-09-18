package uk.intenso.hwan.ex;

public class ConstructorNotFoundException extends RuntimeException {
    public ConstructorNotFoundException(String s) {
        super(s);
    }
}
