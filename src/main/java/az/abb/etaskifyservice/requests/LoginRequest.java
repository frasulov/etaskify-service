package az.abb.etaskifyservice.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {

    @Size(min = 3, max = 32)
    private String username;

    @Size(min = 6, max = 20)
    private String password;
}
