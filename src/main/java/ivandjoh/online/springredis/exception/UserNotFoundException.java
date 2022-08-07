package ivandjoh.online.springredis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7428051251365675318L;

    public UserNotFoundException(String message) {
        super(message);
    }

}
