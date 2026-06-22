package org.scoula.board.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.domain.BoardVO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RootConfig.class })
@Log4j2
class BoardMapperTest {

    @Autowired
    private BoardMapper mapper;

    @Test
    void getList() {
        List<BoardVO> list = mapper.getList();
        //{ boardVO, boardVO, ....}
        for(BoardVO board : list){
            log.info(board);
        }
    }


    @Test
    void getNo() {
        BoardVO board = mapper.get(1L);
        log.info(board);
    }

    @Test
    void create() {
        BoardVO board = new BoardVO();
        board.setTitle("test2");
        board.setContent("test2");
        board.setWriter("test2");

        mapper.create(board);
        log.info(board);
    }
}