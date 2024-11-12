package com.lab.board.service.board;

import com.lab.board.service.dto.board.BoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface BoardService {

    Map<String, String> create(BoardDTO board);

    BoardDTO read(Long id);

    BoardDTO update(Long id,BoardDTO board);

    Map<String, String> delete(Long id);


    Page<BoardDTO> list( BoardDTO board,Pageable pageable);

}
