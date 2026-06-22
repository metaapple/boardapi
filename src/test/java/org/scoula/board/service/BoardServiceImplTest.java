package org.scoula.board.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.dto.BoardDTO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class} )
@Log4j2
class BoardServiceImplTest {

    @Autowired
    private BoardService service;

    @Test
    void getList() {
        List<BoardDTO> list =  service.getList();
        log.warn(list.size());
        for(BoardDTO board : list){
            System.out.println(board);
        }
    }

    @Test
    void get() {
        BoardDTO board = service.get(1L);
        log.warn(board);
    }

    @Test
    void create() {
        BoardDTO board = new BoardDTO();
        board.setTitle("새로작성하는글");
        board.setContent("새로작성하는내용");
        board.setWriter("user1");

        service.create(board);
        log.warn("생성된게시물의번호: " + board.getNo());
    }
}