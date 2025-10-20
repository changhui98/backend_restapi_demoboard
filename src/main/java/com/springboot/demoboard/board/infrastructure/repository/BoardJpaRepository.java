package com.springboot.demoboard.board.infrastructure.repository;

import com.springboot.demoboard.board.domain.entity.Board;
import com.springboot.demoboard.board.domain.entity.BoardStatus;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardJpaRepository extends JpaRepository<Board, UUID> {

    List<Board> findAllByStatus(BoardStatus status);

    @Query("SELECT b from Board b left join fetch b.commentList where b.id = :id")
    Optional<Board> findByIdWithCommentList(@Param("id") UUID id);
}
