package com.springboot.demoboard.board.presentation.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardUpdateResponseDto {

    private String title;
    private String content;
    private LocalDateTime updatedAt;

}
