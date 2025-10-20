package com.springboot.demoboard.board.presentation.dto.response;

import com.springboot.demoboard.board.domain.entity.Board;
import com.springboot.demoboard.comment.domain.entity.Comment;
import com.springboot.demoboard.comment.presentation.dto.response.CommentResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardResponseDto {

    private UUID id;
    private String title;
    private String content;
    private List<CommentResponseDto> commentList;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

}
