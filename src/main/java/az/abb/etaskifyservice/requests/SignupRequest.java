package az.abb.etaskifyservice.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupRequest {

    @Size(min = 2, max = 32)
    private String organisation_name;

    @Size(min = 5, max = 16, message = "Phone number is not valid")
    private String phone_number;

    @Size(min = 3, max = 32)
    private String username;

    @Email(message = "Email is not valid")
    private String email;

    @Size(min = 3, max = 20)
    private String password;
}
