package com.springboot.demoboard.board.application.service;

import com.springboot.demoboard.board.domain.entity.Board;
import com.springboot.demoboard.board.domain.entity.BoardStatus;
import com.springboot.demoboard.board.domain.repository.BoardRepository;
import com.springboot.demoboard.board.presentation.dto.request.BoardDto;
import com.springboot.demoboard.board.presentation.dto.response.BoardResponseDto;
import com.springboot.demoboard.board.presentation.dto.response.BoardUpdateResponseDto;
import com.springboot.demoboard.comment.domain.entity.Comment;
import com.springboot.demoboard.comment.domain.repository.CommentRepository;
import com.springboot.demoboard.comment.presentation.dto.response.CommentResponseDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public List<BoardResponseDto> getAllBoards() {
        List<Board> boards = boardRepository.boardList();
        List<BoardResponseDto> boardResponseDtoList = new ArrayList<>();

        for (Board board : boards) {
            BoardResponseDto boardResponseDto = BoardResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .build();
            boardResponseDtoList.add(boardResponseDto);

        }
        return boardResponseDtoList;
    }

    public BoardResponseDto createBoard(BoardDto boardDto) {
        Board board = Board.of(boardDto);
        Board saveBoard = boardRepository.insertBoard(board);

        return BoardResponseDto.builder()
            .id(saveBoard.getId())
            .title(saveBoard.getTitle())
            .content(saveBoard.getContent())
            .build();
    }

    public BoardResponseDto getBoard(UUID id) throws Exception {
        Board selectBoard = boardRepository.selectBoard(id);

        if (selectBoard.getStatus() == BoardStatus.CLOSED) {
            throw new Exception("선택한 게시글은 존재하지 않습니다.");
        }
        
        List<Comment> commentList = selectBoard.getCommentList();
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        for (Comment comment : commentList) {
            CommentResponseDto exchangeComment = CommentResponseDto.builder()
                .id(comment.getId())
                .title(comment.getTitle())
                .comment(comment.getComment())
                .build();
            commentResponseDtoList.add(exchangeComment);
        }

        selectBoard.getCommentList().addAll(commentList);

        return BoardResponseDto.builder()
            .id(selectBoard.getId())
            .title(selectBoard.getTitle())
            .content(selectBoard.getContent())
            .commentList(commentResponseDtoList)
            .createdAt(selectBoard.getCreateAt())
            .updateAt(selectBoard.getUpdateAt())
            .build();
    }

    public BoardUpdateResponseDto updateBoard(UUID id, BoardDto boardDto) {
        Board selectBoard = boardRepository.selectBoard(id);

        selectBoard.changeTitleAndContent(boardDto.getTitle(), boardDto.getContent());

        Board updateBoard = boardRepository.updateBoard(selectBoard);

        return BoardUpdateResponseDto.builder()
            .title(updateBoard.getTitle())
            .content(updateBoard.getContent())
            .updatedAt(selectBoard.getUpdateAt())
            .build();
    }

    public void deleteBoard(UUID id) {
        Board selectBoard = boardRepository.selectBoard(id);
        Board deleteBoard = selectBoard.closeStatus(selectBoard);
        boardRepository.updateBoard(deleteBoard);
    }
}
