package org.example.service;

import org.example.dto.NoteRequestDto;
import org.example.dto.NoteResponseDto;

import java.util.List;

public interface NoteService {
    List<NoteResponseDto> allUserNotes();
    NoteResponseDto add(NoteRequestDto noteRequestDto);
    Integer numberUserNotes();
}
