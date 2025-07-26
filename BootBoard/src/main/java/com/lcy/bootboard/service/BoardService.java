package com.lcy.bootboard.service;

import com.lcy.bootboard.dto.BoardDTO;

public interface BoardService {

    Long register(BoardDTO boardDTO);

    BoardDTO readOne(Long bno);

    void modify(BoardDTO boardDTO);

    void remove(Long bno);
}
