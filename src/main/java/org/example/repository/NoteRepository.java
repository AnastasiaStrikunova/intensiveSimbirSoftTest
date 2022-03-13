package org.example.repository;

import org.example.entity.NoteEntity;
import org.example.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    @Query(value = "SELECT note.id_note, note.text, note.creation_date, note.id_user FROM note WHERE note.id_user = ?1", nativeQuery = true)
    List<NoteEntity> findAllUserNotes(Long idUser);
    Integer countByUserEntity(UserEntity userEntity);
}
