package com.smartect.repository;

import com.smartect.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Integer> {
    Board findByTitle(String title);
    Board findByTitleAndContent(String title,String content);
    List<Board> findByTitleLike(String keyword);

}
