package com.springboot.demoboard.board.presentation.controller;

import com.springboot.demoboard.board.application.service.BoardService;
import com.springboot.demoboard.board.presentation.dto.request.BoardDto;
import com.springboot.demoboard.board.presentation.dto.response.BoardResponseDto;
import com.springboot.demoboard.board.presentation.dto.response.BoardUpdateResponseDto;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getAllBoards() throws Exception{

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(boardService.getAllBoards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable UUID id) throws Exception {
        BoardResponseDto boardResponseDto = boardService.getBoard(id);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(boardResponseDto);
    }

    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody BoardDto boardDto) {
        BoardResponseDto createBoard = boardService.createBoard(boardDto);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createBoard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardUpdateResponseDto> updateBoard(@PathVariable UUID id, @RequestBody BoardDto boardDto) {
        BoardUpdateResponseDto boardUpdateResponseDto = boardService.updateBoard(id, boardDto);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(boardUpdateResponseDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable UUID id) {
        boardService.deleteBoard(id);
    }

}
