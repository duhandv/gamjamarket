package project.duhan.gamjamarket.common;

import lombok.Getter;

@Getter
public class ErrorResult {

    private final String message;

    private ErrorResult(String message) {
        this.message = message;
    }

    public static ErrorResult of(String message) {
        return new ErrorResult(message);
    }

}
