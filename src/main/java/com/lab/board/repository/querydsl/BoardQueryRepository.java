package com.lab.board.repository.querydsl;

import com.lab.board.domain.QBoard;
import com.lab.board.service.dto.board.BoardDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardQueryRepository {

    private final JPAQueryFactory queryFactory;

    private final QBoard qBoard = new QBoard("qBoard");

    public Page<BoardDTO> qFindAll(Pageable pageable, BoardDTO board) {

        List<BoardDTO> result = queryFactory
                .select(
                        Projections.fields(
                                BoardDTO.class,
                                qBoard.id,
                                qBoard.title,
                                qBoard.writer,
                                qBoard.contents,
                                qBoard.createdBy,
                                formatDate(qBoard.createdDate).as("createdDate"),
                                qBoard.lastModifiedBy,
                                formatDate(qBoard.lastModifiedDate).as("lastModifiedDate")
                        )
                )
                .from(qBoard)
                .where(createConditions(board))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(orderSpecifier(pageable))
                .fetch();

        //토탈 카운트
        Long total = queryFactory
                .select(qBoard.count())
                .from(qBoard)
                .where(createConditions(board))
                .fetchOne();

        //List를 total 카운트 정보를 가지고 Page 객체 생성
        return new PageImpl<>(result, pageable, total != null ? total : 0);
    }
    /**
     * 날짜 필드를 지정된 형식으로 포맷팅합니다.
     *
     * @param dateField 포맷팅할 날짜 필드
     * @return 포맷팅된 날짜 표현식
     */
    private StringTemplate formatDate(Expression<?> dateField) {
        return Expressions.stringTemplate(
                "DATE_FORMAT({0}, {1})",
                dateField,
                "%Y-%m-%d %H:%i:%s"
        );
    }
    /**
     * 다중 필드에 대한 검색 조건 생성
     * @param board 검색 데이터
     * @return 검색 조건
     */
    private BooleanBuilder createConditions(BoardDTO board) {

        BooleanBuilder builder = new BooleanBuilder();

        if (board.getTitle() != null && !board.getTitle().isEmpty()) {
            builder.and(qBoard.title.containsIgnoreCase(board.getTitle()));
        }
        if (board.getWriter() != null && !board.getWriter().isEmpty()) {
            builder.and(qBoard.writer.containsIgnoreCase(board.getWriter()));
        }
        if (board.getContents() != null && !board.getContents().isEmpty()) {
            builder.and(qBoard.contents.containsIgnoreCase(board.getContents()));
        }

        return builder;
    }

    /**
     * 주어진 페이지 객체를 기반으로 정렬을 적용합니다.
     *
     * @param page 정렬 정보를 포함한 페이지 객체
     * @return 쿼리 정렬을 위한 OrderSpecifier
     */
    private OrderSpecifier<?> orderSpecifier(Pageable page) {

        if (page.getSort().isEmpty()) {
            return null;
        }

        Sort.Order order = page.getSort().iterator().next();
        Order direction = order.isAscending() ? Order.ASC : Order.DESC;
        String property = order.getProperty();

        Expression<? extends Comparable<?>> expression = getExpression(property);

        if (expression == null) {
            return null; // 지원하지 않는 속성
        }

        return new OrderSpecifier<>(direction, expression);
    }
    /**
     * 주어진 속성명에 해당하는 Expression 객체를 반환합니다.
     *
     * @param property 속성명
     * @return Expression 객체, 지원하지 않는 경우 null
     */
    private Expression<? extends Comparable<?>> getExpression(String property) {
        return switch (property) {
            case "id" -> qBoard.id;
            case "writer" -> qBoard.writer;
            case "contents" -> qBoard.contents;
            default -> null;
        };
    }

}
