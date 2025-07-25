package com.lcy.bootboard.repository.search;

import com.lcy.bootboard.domain.Board;
import com.lcy.bootboard.domain.QBoard;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;


public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch{


    public BoardSearchImpl() {
        super(Board.class);
    }

    //  복습 필요
    @Override
    public Page<Board> search1(Pageable pageable) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);
        query.where(board.title.contains("1"));

        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> boards = query.fetch();
        Long count = query.fetchCount();

        return null;
    }

    //  복습 및 업그레이드 필요
    //  검색 키워드(정복 미완) :
    //  BooleanBuilder & BooleanExpression, QuerydslRepositorySupport,
    //  orm 체계, 검색 엔진, Page & Slice, PageImpl
    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        if (types != null && types.length > 0 && keyword != null) {
            BooleanBuilder builder = new BooleanBuilder();
            for (String type : types) { //  배열 또는 컬렉션에 저장된 값이 매 반복마다 순서대로 읽혀져 변수에 저장된다.
                switch (type){
                    case "t" :
                        builder.or(board.title.contains(keyword));
                        break;
                    case "c" :
                        builder.or(board.content.contains(keyword));
                        break;
                    case "w" :
                        builder.or(board.writer.contains(keyword));
                        break;
                }
            }
            query.where(builder);
        }
        query.where(board.bno.gt(0L));
        this.getQuerydsl().applyPagination(pageable, query);
        List<Board> boards = query.fetch();
        Long count = query.fetchCount();
        return new PageImpl<>(boards, pageable, count);
    }
}
