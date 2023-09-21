package az.abb.etaskifyservice.exceptions;


import lombok.Getter;

@Getter
public class AccessDeniedException extends Exception {

    public AccessDeniedException(String key, String message) {
        super(key, message);
    }

}
