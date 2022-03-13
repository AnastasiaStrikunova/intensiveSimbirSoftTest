package org.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.security.Role;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_gen")
    @SequenceGenerator(name = "user_id_gen", sequenceName = "seq_user", allocationSize = 1)
    private Long idUser;
    private String email;
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
    @JsonManagedReference
    private List<NoteEntity> noteEntityList;

    public UserEntity(Long idUser) {
        this.idUser = idUser;
    }
}
