package com.springboot.demoboard.comment.infrastructure.repository;

import com.springboot.demoboard.comment.domain.entity.Comment;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByStatusTrue();

    List<Comment> findByBoardId(UUID id);
}
