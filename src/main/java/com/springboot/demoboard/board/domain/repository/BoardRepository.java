package com.springboot.demoboard.board.domain.repository;

import com.springboot.demoboard.board.domain.entity.Board;
import java.util.List;
import java.util.UUID;

public interface BoardRepository {

    List<Board> boardList();

    Board selectBoard(UUID id);

    Board insertBoard(Board board);

    Board updateBoard(Board board);
}
