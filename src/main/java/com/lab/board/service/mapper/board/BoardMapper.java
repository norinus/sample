package com.lab.board.service.mapper.board;


import com.lab.board.domain.Board;
import com.lab.board.service.dto.board.BoardDTO;
import com.lab.board.service.mapper.EntityMapper;
import org.mapstruct.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface BoardMapper extends EntityMapper<BoardDTO, Board> {

    @Override
    BoardDTO toDto(Board board);  // Ensure naming matches EntityMapper

    @Override
    Board toEntity(BoardDTO boardDTO);

    @AfterMapping
    default void formatDatesToDTO(@MappingTarget BoardDTO boardDTO, Board board) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.of("Asia/Seoul"));
        if (board.getCreatedDate() != null) {
            boardDTO.setCreatedDate(formatter.format(board.getCreatedDate()));
        }
        if (board.getLastModifiedDate() != null) {
            boardDTO.setLastModifiedDate(formatter.format(board.getLastModifiedDate()));
        }
    }

    //생성시 값 설정 부분
    @AfterMapping
    default void setDefaultBoard(@MappingTarget Board board) {

        if (board.getCreatedBy() == null) {
            board.setCreatedBy(board.getWriter());
        }

        board.setLastModifiedBy(board.getWriter());

        if (board.getCreatedDate() == null) {
            board.setCreatedDate(Instant.now());
        }

        board.setLastModifiedDate(Instant.now());
    }

}
