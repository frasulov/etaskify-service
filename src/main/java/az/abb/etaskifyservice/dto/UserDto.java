package az.abb.etaskifyservice.dto;

import az.abb.etaskifyservice.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private String id;
    private String email;
}
