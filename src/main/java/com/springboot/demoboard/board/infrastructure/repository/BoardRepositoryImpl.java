package com.springboot.demoboard.board.infrastructure.repository;

import com.springboot.demoboard.board.domain.entity.Board;
import com.springboot.demoboard.board.domain.entity.BoardStatus;
import com.springboot.demoboard.board.domain.repository.BoardRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {

    private final BoardJpaRepository boardJpaRepository;

    @Override
    public List<Board> boardList() {

        return boardJpaRepository.findAllByStatus(BoardStatus.OPEN);
    }

    @Override
    public Board selectBoard(UUID id) {

        return boardJpaRepository.findByIdWithCommentList(id).orElseThrow(
            () -> new IllegalArgumentException("조회할 게시글이 없습니다.")
        );
    }

    @Override
    public Board insertBoard(Board board) {

        return boardJpaRepository.save(board);
    }

    @Override
    public Board updateBoard(Board board) {

        return boardJpaRepository.save(board);
    }
}
