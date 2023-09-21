package az.abb.etaskifyservice.requests;

import az.abb.etaskifyservice.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateTaskRequest {

    @Size(min = 2, max = 128)
    private String title;

    @Size(max = 255)
    private String description;


//    @Future(message = "Deadline must be a future date")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Timestamp deadline;

//    @Pattern(regexp = "^(todo|in_progress|in_review|done)$", message = "Status must be one of [todo, in_progress, in_review, done]")
    private TaskStatus status;
}
