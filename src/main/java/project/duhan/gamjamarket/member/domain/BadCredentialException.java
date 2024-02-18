package project.duhan.gamjamarket.member.domain;

public class BadCredentialException extends RuntimeException {

    public BadCredentialException() {
    }

    public BadCredentialException(String message) {
        super(message);
    }

    public BadCredentialException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadCredentialException(Throwable cause) {
        super(cause);
    }

    public BadCredentialException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
