package org.example.controller;

import org.example.dto.NoteRequestDto;
import org.example.dto.NoteResponseDto;
import org.example.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${api-base-url}/note")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<List<NoteResponseDto>> allUserNotes(){
        return ResponseEntity.ok(noteService.allUserNotes());
    }

    @PostMapping
    public ResponseEntity<NoteResponseDto> add(@RequestBody NoteRequestDto noteRequestDto){
        return ResponseEntity.ok(noteService.add(noteRequestDto));
    }

    @GetMapping("/number")
    public ResponseEntity<Integer> numberUserNotes(){
        return ResponseEntity.ok(noteService.numberUserNotes());
    }
}
