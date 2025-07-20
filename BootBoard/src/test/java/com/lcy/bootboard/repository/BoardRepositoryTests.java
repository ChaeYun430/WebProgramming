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
        Board board = result.orElseThrow();
        log.info(board);
    }
}
