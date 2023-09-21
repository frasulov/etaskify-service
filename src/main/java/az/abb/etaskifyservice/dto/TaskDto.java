package az.abb.etaskifyservice.dto;

import az.abb.etaskifyservice.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskDto {

    private Integer id;
    private String title;

    private String description;

    private Timestamp deadline;

    private TaskStatus status;

    @JsonProperty("created_at")
    private String createdAt;

    private Set<UserDto> users;
}
