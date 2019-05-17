package exceptions;

public class APIErrorException extends Exception {

    public APIErrorException(String message) {
        super(message);
    }

    public APIErrorException() {
    }

}
