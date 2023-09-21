package az.abb.etaskifyservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "organisations")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column
    private String address;

    @OneToMany(mappedBy = "organisation")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "organisation")
    private Set<Task> tasks = new HashSet<>();

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamp default now()")
    private Timestamp created_at;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "timestamp default now()")
    private Timestamp updated_at;

}
