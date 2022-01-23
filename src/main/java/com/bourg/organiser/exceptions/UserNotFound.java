package com.bourg.organiser.exceptions;

/**
 * Created by Bourg, Ambrose on 23/01/2022
 */
public class UserNotFound extends RuntimeException {

    public UserNotFound(String message) {
        super(message);
    }

    public UserNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFound(Throwable cause) {
        super(cause);
    }

}
