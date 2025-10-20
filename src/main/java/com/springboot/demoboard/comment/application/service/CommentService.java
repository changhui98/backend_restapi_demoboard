package com.springboot.demoboard.comment.application.service;

import com.springboot.demoboard.board.domain.entity.Board;
import com.springboot.demoboard.board.domain.repository.BoardRepository;
import com.springboot.demoboard.comment.domain.entity.Comment;
import com.springboot.demoboard.comment.domain.repository.CommentRepository;
import com.springboot.demoboard.comment.presentation.dto.request.CommentDto;
import com.springboot.demoboard.comment.presentation.dto.response.CommentResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public CommentResponseDto comments(UUID id, CommentDto commentDto) {
        Board selectBoard = boardRepository.selectBoard(id);
        Comment comment = Comment.of(commentDto, selectBoard);
        Comment saveComment = commentRepository.save(comment);

        return CommentResponseDto.builder()
            .id(saveComment.getId())
            .title(saveComment.getTitle())
            .comment(saveComment.getComment())
            .build();
    }
}
