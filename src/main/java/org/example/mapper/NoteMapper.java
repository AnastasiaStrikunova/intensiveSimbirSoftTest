package org.example.mapper;

import org.example.DateParser;
import org.example.dto.NoteRequestDto;
import org.example.dto.NoteResponseDto;
import org.example.entity.NoteEntity;
import org.example.entity.UserEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface NoteMapper {
    @AfterMapping
    default void setEntity(@MappingTarget NoteEntity noteEntity, NoteRequestDto noteRequestDto){
        if (noteRequestDto.getIdUser() != null) noteEntity.setUserEntity(new UserEntity(noteRequestDto.getIdUser()));
    }
    NoteEntity noteRequestDtoToNoteEntity(NoteRequestDto noteRequestDto);

    @AfterMapping
    default void setId(@MappingTarget NoteResponseDto noteResponseDto, NoteEntity noteEntity){
        if (noteEntity.getUserEntity() != null) noteResponseDto.setIdUser(noteEntity.getUserEntity().getIdUser());

        if (noteEntity.getCreationDate() != null) {
            DateParser dateParser = new DateParser();
            noteResponseDto.setDate(dateParser.getDate(noteEntity.getCreationDate()));
            noteResponseDto.setTime(dateParser.getTime(noteEntity.getCreationDate()));
        }
    }
    NoteResponseDto noteEntityToNoteResponseDto(NoteEntity noteEntity);
}
