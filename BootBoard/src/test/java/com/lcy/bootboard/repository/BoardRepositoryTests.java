package com.lcy.bootboard.repository;

import com.lcy.bootboard.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
                    log.info("bno" + result.getBno());
                }
        );
    }

    @Test
    public void testSelect(){
        Long bno = 100L;
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
    //https://ittrue.tistory.com/254  읽어보기... 지금은 힘드러어어어

    
}
