package org.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "note")
public class NoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_id_gen")
    @SequenceGenerator(name = "note_id_gen", sequenceName = "seq_note", allocationSize = 1)
    private Long idNote;
    private String text;
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonBackReference
    private UserEntity userEntity;
}
