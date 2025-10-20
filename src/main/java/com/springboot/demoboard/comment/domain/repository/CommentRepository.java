package com.springboot.demoboard.comment.domain.repository;

import com.springboot.demoboard.comment.domain.entity.Comment;
import com.springboot.demoboard.comment.presentation.dto.request.CommentDto;
import com.springboot.demoboard.comment.presentation.dto.response.CommentResponseDto;
import java.util.List;
import java.util.UUID;

public interface CommentRepository {

    Comment save(Comment comment);
    List<Comment> findByBoardId(UUID id);


}
