package com.lab.board.service.criteria.board;

import com.lab.board.domain.Board;
import com.lab.board.service.dto.board.BoardDTO;
import org.springframework.data.jpa.domain.Specification;

public class BoardCriteria {

    public static Specification<Board> byCriteria(BoardDTO dto) {

        return (root, query, criteriaBuilder) -> {

            Specification<Board> spec = Specification.where(null);

            if (dto.getTitle() != null && !dto.getTitle().isEmpty()) {
                spec = spec.and((root1, query1, cb) -> cb.like(root1.get("title"), "%" + dto.getTitle() + "%"));
            }

            if (dto.getWriter() != null && !dto.getWriter().isEmpty()) {
                spec = spec.and((root1, query1, cb) -> cb.equal(root1.get("writer"), dto.getWriter()));
            }

            if (dto.getContents() != null && !dto.getContents().isEmpty()) {
                spec = spec.and((root1, query1, cb) -> cb.like(root1.get("contents"), "%" + dto.getContents() + "%"));
            }

            return spec.toPredicate(root, query, criteriaBuilder);
        };
    }
}
