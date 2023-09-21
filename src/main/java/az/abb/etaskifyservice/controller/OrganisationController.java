package az.abb.etaskifyservice.controller;

import az.abb.etaskifyservice.config.Constants;
import az.abb.etaskifyservice.dto.ListTasksMeta;
import az.abb.etaskifyservice.dto.TaskDto;
import az.abb.etaskifyservice.exceptions.AccessDeniedException;
import az.abb.etaskifyservice.exceptions.ResourceAlreadyExist;
import az.abb.etaskifyservice.exceptions.UsernameAlreadyExist;
import az.abb.etaskifyservice.requests.AssignUserToTaskRequest;
import az.abb.etaskifyservice.requests.CreateTaskRequest;
import az.abb.etaskifyservice.requests.CreateUserRequest;
import az.abb.etaskifyservice.responses.Response;
import az.abb.etaskifyservice.service.OrganisationService;
import az.abb.etaskifyservice.service.TaskService;
import az.abb.etaskifyservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/organisation")
@RequiredArgsConstructor
public class OrganisationController {

    private final UserService userService;
    private final TaskService taskService;

    @PostMapping("/users")
    public ResponseEntity<Response> createUser(@RequestBody @Valid CreateUserRequest request) {
        try {
            userService.createUser(request);

            return ResponseEntity.ok(Response.builder().message(Constants.USER_CREATED).build());
        } catch (UsernameAlreadyExist e) {
            Response response = Response.builder()
                    .message(Constants.REQUEST_FAILED).build()
                    .setErrorData(e.getKey(), e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/tasks")
    public ResponseEntity<Response> createTask(@RequestBody @Valid CreateTaskRequest request) {
        taskService.save(request);

        return ResponseEntity.ok(Response.builder().message(Constants.TASK_CREATED).build());
    }

    @GetMapping("/tasks")
    public ResponseEntity<Response> getTasks() {
        List<TaskDto> tasks = taskService.listTasks();

        ListTasksMeta meta = ListTasksMeta.builder().count(tasks.size()).build();

        return ResponseEntity.ok(Response.builder()
                .message(Constants.TASKS_RETRIEVED)
                .data(tasks)
                .meta(meta).build());
    }

    @PutMapping("/task/{id}/assignee/{assigneeId}")
    public ResponseEntity<Response> assignUserToTask(@PathVariable Integer id, @PathVariable Integer assigneeId) {
        AssignUserToTaskRequest request = AssignUserToTaskRequest.builder().taskId(id).assigneeId(assigneeId).build();

        try {
            taskService.assignUsersToTask(request);

            return ResponseEntity.ok(Response.builder()
                    .message(Constants.USER_ASSIGNED).build());
        } catch (AccessDeniedException | ResourceAlreadyExist e) {
            Response response = Response.builder()
                    .message(Constants.REQUEST_FAILED).build()
                    .setErrorData(e.getKey(), e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }
}
