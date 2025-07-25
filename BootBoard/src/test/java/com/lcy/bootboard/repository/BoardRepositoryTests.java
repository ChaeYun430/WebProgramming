package com.lcy.bootboard.repository;

import com.lcy.bootboard.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;
    //boardRepository 메서드 실행 결과 리턴 타입에 집중
    //JPA를 이용한다 == 영속 콘텍스트와 데이터베이스를 동기화해서 관리한다

    //Builder : 배열이나 컬렉션을 통한 생성이 아닌 직접 원하는 값을 넣어 생성 가능, build 메서드를 통해 스트림을 리턴
    @Test
    public void testInsert(){
        IntStream.rangeClosed(1, 100).forEach(
                i->{
                    Board board = Board.builder()
                            .title("제목 : " + i)
                            .content("내용 : " + i)
                            .writer("작성자 : " + (i % 10)).build();
                    Board result = boardRepository.save(board);
                    log.info("bno", result.getBno());
                }
        );
    }

    @Test
    public void testSelect(){
        Long bno = 99L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow(); //Optional의 인자가 null일 경우 예외처리를 시킨다.
        log.info(board);
    }

    @Test
    public void testUpdate(){
        Long bno = 100L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        board.change("update 100 title", "update 100 content");
        boardRepository.save(board);
        //조회와 입력 활용
    }

    @Test
    public void testDelete(){
        Long bno = 100L;
        boardRepository.deleteById(bno);
    }


    @Test
    public void testPaging(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        //  PageRequest.of ( 시작 페이지, 사이즈, 정렬 조건, 속성... )
        //  sort by : 특정 필드(열)를 기준으로 데이터를 정렬할 때 사용되는 명령어
        Page<Board> result = boardRepository.findAll(pageable);
        //  대부분 Pageable 파라미터를 이용하면 메서드의 리턴 타입은 Page<T>으로 설계한다.
        //  Page<T> 기능 익히기

        log.info("total count", result.getTotalElements());
        log.info("total pages", result.getTotalPages());
        log.info("page number", result.getNumber());
        log.info("page size", result.getSize());
        List<Board> todoList = result.getContent();
        todoList.forEach(board -> log.info(board));
    }

    //  쿼리메서드 : 보통 SQL에서 사용하는 칼럼들을 같이 결합해서 구성하면 그 자체가 JPA에서 사용하는 쿼리가 되는 기능
    //  JPQL : @Query의 value로 작성하는 문자열로 JPA에서 사용하는 쿼리 언어
    //  Querydsl : JPA의 구현체인 Hiberate 프레임워크가 사용하는 HQL을 동적으로 생성할 수 있는 프레임워크
    //  Querydsl의 목적 : 타입 기반으ㄹ 코드를 이용해서 JPQL 쿼리를 생성하고 실행하는 것

    @Test
    public void testSearch1(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        boardRepository.search1(pageable);
    }

    @Test
    public void testSearchAll(){
        String[] types = {"t", "c", "w"};
        String keyword = "1";
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        log.info(result.getTotalPages());
        log.info(result.getSize());
        log.info(result.getNumber());
        log.info("hasPrevious : "+ result.hasPrevious());
        log.info("hasNext : "+ result.hasNext());
        result.getContent().forEach(board -> log.info(board));
    }
}
