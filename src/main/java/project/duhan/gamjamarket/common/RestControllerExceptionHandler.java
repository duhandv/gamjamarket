package project.duhan.gamjamarket.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.duhan.gamjamarket.member.domain.BadCredentialException;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(BadCredentialException.class)
    public ResponseEntity<ErrorResult> handlerBadCredentialException(BadCredentialException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResult(exception.getMessage()));
    }

}
