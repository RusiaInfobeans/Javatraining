package TweeterProject.Exception;

import org.springframework.http.HttpStatus;

public class CommonException extends RuntimeException {


    private final HttpStatus status;

    public CommonException(String message, HttpStatus httpStatus) {
        super(message);
        this.status = httpStatus;
    }
}