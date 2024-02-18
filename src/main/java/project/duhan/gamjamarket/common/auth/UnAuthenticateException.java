package project.duhan.gamjamarket.common.auth;

public class UnAuthenticateException extends RuntimeException {

    public UnAuthenticateException() {
        super();
    }

    public UnAuthenticateException(String message) {
        super(message);
    }

    public UnAuthenticateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnAuthenticateException(Throwable cause) {
        super(cause);
    }

    protected UnAuthenticateException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
