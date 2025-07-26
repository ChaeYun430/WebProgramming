package com.lcy.bootboard.service;

import com.lcy.bootboard.dto.BoardDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister(){
        log.info(boardService.getClass().getName());
        //프록시 객체가 출력

        BoardDTO boardDTO = BoardDTO.builder()
                .title("test...title")
                .content("test...content")
                .writer("test...writer")
                .build();

        Long bno = boardService.register(boardDTO);
        log.info("bno" + bno);
    }

    @Test
    public void testModify(){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("testModify...title")
                .content("testModify...content")
                .build();
        boardService.modify(boardDTO);
    }
}
