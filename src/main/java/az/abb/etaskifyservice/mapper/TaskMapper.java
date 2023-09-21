package az.abb.etaskifyservice.mapper;


import az.abb.etaskifyservice.dto.TaskDto;
import az.abb.etaskifyservice.entity.Organisation;
import az.abb.etaskifyservice.entity.Task;
import az.abb.etaskifyservice.requests.CreateTaskRequest;
import az.abb.etaskifyservice.requests.SignupRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaskMapper {
    public Task toTask(CreateTaskRequest request) {
        return Task.builder()
                .title(request.getTitle())
                .deadline(request.getDeadline())
                .description(request.getDescription())
                .status(request.getStatus())
                .build();
    }

    public TaskDto toTaskDto(Task task) {
        return TaskDto.builder()
                .title(task.getTitle())
                .deadline(task.getDeadline())
                .description(task.getDescription())
                .status(task.getStatus())
                .createdAt(task.getCreated_at().toString())
                .id(task.getId())
                .users(UserMapper.INSTANCE.toDtoList(task.getUsers()))
                .build();
    }

    public List<TaskDto> toTaskDtoList(List<Task> tasks) {
        List<TaskDto> dtoList = new ArrayList<>();

        for (Task task: tasks) {
            dtoList.add(toTaskDto(task));
        }

        return dtoList;
    }
}
