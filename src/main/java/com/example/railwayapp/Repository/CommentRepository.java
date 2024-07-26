package com.example.railwayapp.Repository;

import com.example.railwayapp.Model.Entity.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Comment c WHERE c.time < :olderThan")
    void deleteOlderComments(@Param("olderThan") LocalDateTime olderThan);
}
