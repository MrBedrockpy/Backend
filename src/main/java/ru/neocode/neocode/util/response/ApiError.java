package ru.neocode.neocode.util.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class ApiError {

    private final int code;
    private final String message;

    private ApiError(HttpStatusCode code, String message) {
        this.code = code.value();
        this.message = message;
    }

    public static ApiError badRequest(String message) {
        return new ApiError(HttpStatus.BAD_REQUEST, message);
    }

    public static ApiError unauthorized(String message) {
        return new ApiError(HttpStatus.UNAUTHORIZED, message);
    }

    public static ApiError notFound(String message) {
        return new ApiError(HttpStatus.NOT_FOUND, message);
    }

    public static ApiError internal(String message) {
        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

}