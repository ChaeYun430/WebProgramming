package com.spring.bootboard.repository;

import com.spring.bootboard.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> { //엔티티 타입과 @Id

}
