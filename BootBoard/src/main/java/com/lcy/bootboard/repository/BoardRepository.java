package com.lcy.bootboard.repository;

import com.lcy.bootboard.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> { //엔티티 타입과 @Id

}
