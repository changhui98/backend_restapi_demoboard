package com.springboot.demoboard.comment.infrastructure.repository;

import com.springboot.demoboard.comment.domain.entity.Comment;
import com.springboot.demoboard.comment.domain.repository.CommentRepository;
import com.springboot.demoboard.comment.presentation.dto.request.CommentDto;
import com.springboot.demoboard.comment.presentation.dto.response.CommentResponseDto;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    @Override
    public Comment save(Comment comment) {

        return commentJpaRepository.save(comment);
    }

    @Override
    public List<Comment> findByBoardId(UUID id) {

        return commentJpaRepository.findByBoardId(id);
    }
}
