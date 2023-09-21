package az.abb.etaskifyservice.repository;

import az.abb.etaskifyservice.entity.Organisation;
import az.abb.etaskifyservice.entity.Task;
import az.abb.etaskifyservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Iterable<Task> getTasksByOrganisation(Organisation organisation);

    @Query(value = "select case when count(*) = 1 then true else false end from task_assignees where task_id=? and user_id =?", nativeQuery = true)
    Boolean doesAssigneeExist(Integer taskId, Integer userId);

    @Modifying
    @Transactional
    @Query(value = "insert into task_assignees(task_id, user_id) values (?, ?)", nativeQuery = true)
    void assignUserToTask(Integer taskId, Integer userId);

}