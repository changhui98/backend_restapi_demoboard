package com.springboot.demoboard.comment.presentation.dto.response;

import com.springboot.demoboard.board.domain.entity.Board;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {

    private UUID id;
    private String title;
    private String comment;

}
