package az.abb.etaskifyservice.service;

import az.abb.etaskifyservice.dto.TaskDto;
import az.abb.etaskifyservice.entity.Task;
import az.abb.etaskifyservice.entity.User;
import az.abb.etaskifyservice.exceptions.AccessDeniedException;
import az.abb.etaskifyservice.exceptions.ResourceAlreadyExist;
import az.abb.etaskifyservice.exceptions.ResourceNotFound;
import az.abb.etaskifyservice.mapper.TaskMapper;
import az.abb.etaskifyservice.repository.TaskRepository;
import az.abb.etaskifyservice.requests.AssignUserToTaskRequest;
import az.abb.etaskifyservice.requests.CreateTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final UserService userService;

    public Task save(CreateTaskRequest request) {
        User loggedUser = userService.getLoggedUser();

        Task task = new TaskMapper().toTask(request);
        task.setOrganisation(loggedUser.getOrganisation());

        return taskRepository.save(task);
    }

    public List<TaskDto> listTasks() {
        User loggedUser = userService.getLoggedUser();

        List<Task> tasks = (List<Task>) taskRepository.getTasksByOrganisation(loggedUser.getOrganisation());

        return new TaskMapper().toTaskDtoList(tasks);
    }

    public Task getTaskById(Integer id) throws AccessDeniedException {
        User loggedUser = userService.getLoggedUser();

        Task task = taskRepository.getById(id);

        if (!task.getOrganisation().getId().equals(loggedUser.getOrganisation().getId())) {
            throw new AccessDeniedException("common", "Customer does not have access to do this action.");
        }

        return task;
    }

    public void assignUsersToTask(AssignUserToTaskRequest request) throws AccessDeniedException, ResourceAlreadyExist {
        User loggedUser = userService.getLoggedUser();
        User user;
        Task task;

        try {
            task = getTaskById(request.getTaskId());
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFound("common", "Task not found");
        }

        try {
            user = userService.getUserById(request.getAssigneeId());
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFound("common", "User not found");
        }

        if (!user.getOrganisation().getId().equals(loggedUser.getOrganisation().getId())) {
            throw new AccessDeniedException("common", "Customer does not have access to do this action.");
        }

        Boolean exist = taskRepository.doesAssigneeExist(request.getTaskId(), request.getAssigneeId());
        if (exist) {
            throw new ResourceAlreadyExist("common", "The user already assigned to task.");
        }

        taskRepository.assignUserToTask(request.getTaskId(), request.getAssigneeId());
    }
}
