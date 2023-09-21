package az.abb.etaskifyservice.exceptions;


import lombok.Getter;

@Getter
public class ResourceNotFound extends Exception {

    public ResourceNotFound(String key, String message) {
        super(key, message);
    }

}
