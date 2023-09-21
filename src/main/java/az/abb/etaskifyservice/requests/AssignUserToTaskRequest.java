package az.abb.etaskifyservice.requests;

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
