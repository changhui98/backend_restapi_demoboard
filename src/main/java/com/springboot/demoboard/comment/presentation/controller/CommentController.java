package com.springboot.demoboard.comment.presentation.controller;

import com.springboot.demoboard.comment.application.service.CommentService;
import com.springboot.demoboard.comment.presentation.dto.request.CommentDto;
import com.springboot.demoboard.comment.presentation.dto.response.CommentResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}")
    public ResponseEntity<CommentResponseDto> comments(@PathVariable UUID id, @RequestBody CommentDto commentDto){
        CommentResponseDto commentResponseDto = commentService.comments(id, commentDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentResponseDto);
    }
}
