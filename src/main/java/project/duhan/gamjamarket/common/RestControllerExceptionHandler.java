package project.duhan.gamjamarket.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.duhan.gamjamarket.common.auth.UnAuthenticateException;
import project.duhan.gamjamarket.member.domain.BadCredentialException;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(BadCredentialException.class)
    public ResponseEntity<ErrorResult> handlerBadCredentialException(BadCredentialException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorResult.of(exception.getMessage()));
    }

    @ExceptionHandler(UnAuthenticateException.class)
    public ResponseEntity<ErrorResult> handlerUnAuthenticateException(UnAuthenticateException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResult.of(exception.getMessage()));
    }

}
