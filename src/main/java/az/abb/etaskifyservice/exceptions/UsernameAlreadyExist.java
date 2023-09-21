package az.abb.etaskifyservice.exceptions;


import lombok.Getter;

@Getter
public class UsernameAlreadyExist extends Exception {

    public UsernameAlreadyExist(String key, String message) {
        super(key, message);
    }

}
