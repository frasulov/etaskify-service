package az.abb.etaskifyservice.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserRequest {

    @Size(min = 2, max = 32)
    private String name;

    @Size(min = 2, max = 32)
    private String surname;

    @Email(message = "Email is not valid")
    private String email;
}
