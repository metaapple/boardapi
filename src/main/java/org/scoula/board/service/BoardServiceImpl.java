package org.scoula.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //스프링 시작할 때 스캔해서 싱글톤으로 만들어줌.
@RequiredArgsConstructor //생성자호출할 때 Autowired해줌.
@Log4j2
public class BoardServiceImpl implements BoardService{

//    @Autowired
    final private BoardMapper mapper;

    @Override
    public List<BoardDTO> getList() {

        //전처리하고
        //db처리해달라고 요청
        //db처리 싱글톤 객체 여기에서 써야함.
        return mapper.getList().stream().map(BoardDTO::of).toList();
        //List<BoardVO> --> Stream<BoardVO> --> Stream<BoardDTO> --> List<BoardDTO>
    }

    @Override
    public BoardDTO get(Long no) {
        log.info("서비스의 get() 호출됨...");
        return BoardDTO.of(mapper.get(no));
    }

    @Override
    public BoardDTO create(BoardDTO board) {
        BoardVO vo = board.toVo();
        mapper.create(vo);
        board.setNo(vo.getNo()); //dto에 db에서 생성된 no를 넣어줌.
        return get(vo.getNo());
    }

    @Override
    public BoardDTO update(BoardDTO board) {
        log.info("update......" + board);
        mapper.update(board.toVo());
        return get(board.getNo());
    }

    @Override
    public BoardDTO delete(Long no) {
        log.info("delete...." + no);
        BoardDTO board = get(no);
        mapper.delete(no);
        return board;
    }
}
