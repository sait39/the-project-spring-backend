package com.github.sait39.the_project_backend.repository;

import com.github.sait39.the_project_backend.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("""
            SELECT n FROM Note n WHERE (:title IS NULL OR n.title = :title) 
            AND (:content IS NULL OR n.content LIKE %:content%)"""
    )
    List<Note> findByTitleOrContent(@Param("title") String title, @Param("content") String content);


    @Query("SELECT COUNT(n) FROM Note n WHERE n.createdDate = :createdDate")
    Long countNotesByCreatedDate(@Param("createdDate") LocalDate createdDate);

    @Query("SELECT n.createdDate, COUNT(n) FROM Note n GROUP BY n.createdDate")
    List<Object[]> countNotesGroupedByDate();
}
