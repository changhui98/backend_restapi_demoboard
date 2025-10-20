package com.springboot.demoboard.board.domain.entity;

import com.springboot.demoboard.board.presentation.dto.request.BoardDto;
import com.springboot.demoboard.comment.domain.entity.Comment;
import com.springboot.demoboard.global.doamin.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "board")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "board_id")
    private UUID id;

    private String title;

    private String content;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();

    @Enumerated
    private BoardStatus status;

    public static Board of(BoardDto boardDto) {
        Board board = new Board();
        board.title = boardDto.getTitle();
        board.content = boardDto.getContent();
        board.status = BoardStatus.OPEN;

        return board;
    }

    public void changeTitleAndContent(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Board closeStatus(Board board) {
        board.status = BoardStatus.CLOSED;
        return this;
    }
}