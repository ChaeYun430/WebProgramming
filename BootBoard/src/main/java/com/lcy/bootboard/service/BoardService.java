package com.lcy.bootboard.service;

import com.lcy.bootboard.dto.BoardDTO;
import com.lcy.bootboard.dto.PageRequestDTO;
import com.lcy.bootboard.dto.PageResponseDTO;

public interface BoardService {

    Long register(BoardDTO boardDTO);

    BoardDTO readOne(Long bno);

    void modify(BoardDTO boardDTO);

    void remove(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}