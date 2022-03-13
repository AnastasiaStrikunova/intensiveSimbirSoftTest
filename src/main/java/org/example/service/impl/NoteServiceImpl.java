package org.example.service.impl;

import org.example.dto.NoteRequestDto;
import org.example.dto.NoteResponseDto;
import org.example.entity.NoteEntity;
import org.example.exception.NotFoundException;
import org.example.mapper.NoteMapper;
import org.example.repository.NoteRepository;
import org.example.security.UserDetailsServiceImpl;
import org.example.service.NoteService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper = Mappers.getMapper(NoteMapper.class);
    private final UserDetailsServiceImpl userDetailsService;

    public NoteServiceImpl(NoteRepository noteRepository, UserDetailsServiceImpl userDetailsService) {
        this.noteRepository = noteRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteResponseDto> allUserNotes() {
        List<NoteEntity> noteEntityList = new ArrayList<>(noteRepository.findAllUserNotes(userDetailsService.getUserEntity().getIdUser()));
        List<NoteResponseDto> noteResponseDtoList = new ArrayList<>();
        noteEntityList.forEach(noteEntity -> noteResponseDtoList.add(noteMapper.noteEntityToNoteResponseDto(noteEntity)));
        return noteResponseDtoList;
    }

    @Override
    @Transactional
    public NoteResponseDto add(NoteRequestDto noteRequestDto) {
        check(noteRequestDto);
        noteRequestDto.setCreationDate(new Date());
        noteRequestDto.setIdUser(userDetailsService.getUserEntity().getIdUser());
        NoteEntity noteEntity = noteMapper.noteRequestDtoToNoteEntity(noteRequestDto);
        return noteMapper.noteEntityToNoteResponseDto(noteRepository.save(noteEntity));
    }

    @Override
    @Transactional(readOnly = true)
    public Integer numberUserNotes() {
        return noteRepository.countByUserEntity(userDetailsService.getUserEntity());
    }

    private void check(NoteRequestDto noteRequestDto) {
        if (noteRequestDto.getText() == "") {
            throw new NotFoundException("Текст заметки не может быть пустым");
        }
    }

}
