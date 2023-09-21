package az.abb.etaskifyservice.exceptions;


import lombok.Getter;

@Getter
public class ResourceAlreadyExist extends Exception {

    public ResourceAlreadyExist(String key, String message) {
        super(key, message);
    }

}
