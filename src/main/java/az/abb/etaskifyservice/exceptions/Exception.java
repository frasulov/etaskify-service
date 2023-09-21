package az.abb.etaskifyservice.exceptions;


import lombok.Getter;

@Getter
public class Exception extends RuntimeException {

    private String key;

    public Exception(String key, String message) {
        super(message);

        this.key = key;
    }

}
