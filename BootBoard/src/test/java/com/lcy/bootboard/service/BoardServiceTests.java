package com.lcy.bootboard.service;

import com.lcy.bootboard.dto.BoardDTO;
import com.lcy.bootboard.dto.PageRequestDTO;
import com.lcy.bootboard.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

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

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardDTO> pageResponseDTO = boardService.list(pageRequestDTO);
        log.info(pageResponseDTO);
    }
}
