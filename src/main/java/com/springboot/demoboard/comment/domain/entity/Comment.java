package com.springboot.demoboard.comment.domain.entity;

import com.springboot.demoboard.board.domain.entity.Board;
import com.springboot.demoboard.comment.presentation.dto.request.CommentDto;
import com.springboot.demoboard.global.doamin.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String title;

    private String comment;

    @Enumerated
    private CommentStatus status;

    public static Comment of(CommentDto commentDto,  Board board){
        Comment comment = new Comment();
        comment.board = board;
        comment.title = commentDto.getTitle();
        comment.comment = commentDto.getComment();
        comment.status = CommentStatus.OPEN;

        return comment;
    }
}