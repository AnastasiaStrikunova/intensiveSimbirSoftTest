package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoteRequestDto {
    private Long idNote;
    private String text;
    private Date creationDate;
    private Long idUser;
}
