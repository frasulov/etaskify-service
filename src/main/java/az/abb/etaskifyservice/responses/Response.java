package az.abb.etaskifyservice.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Response {
    private String message;
    private Object data;
    private Object meta;

    public Response setErrorData(String key, String message) {
        HashMap<String, String> errors = new HashMap<>();

        errors.put(key, message);

        this.data = errors;

        return this;
    }
}
