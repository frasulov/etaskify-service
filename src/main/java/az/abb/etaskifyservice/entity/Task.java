package az.abb.etaskifyservice.entity;

import az.abb.etaskifyservice.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Timestamp deadline;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private TaskStatus status = TaskStatus.TODO;

    @ManyToOne
    private Organisation organisation;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamp default now()")
    private Timestamp created_at;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "timestamp default now()")
    private Timestamp updated_at;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinTable(name = "task_assignees",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            foreignKey = @ForeignKey(name = "task_fk_id",
                    foreignKeyDefinition = "foreign key(task_id) references tasks(id) on delete cascade"),
            inverseForeignKey = @ForeignKey(name = "user_fk_id",
                    foreignKeyDefinition = "foreign key(user_id) references users(id) on delete cascade")
    )
    private List<User> users = new ArrayList<>();
}
