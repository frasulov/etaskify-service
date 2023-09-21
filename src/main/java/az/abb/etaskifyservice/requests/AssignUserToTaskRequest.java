package az.abb.etaskifyservice.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AssignUserToTaskRequest {
    private Integer taskId;

    private Integer assigneeId;
}
