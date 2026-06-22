package org.scoula.board.service;

import org.scoula.board.dto.BoardDTO;

import java.util.List;

public interface BoardService {
    //mybatis용 인터페이스에 정의된 메서드는 다 만들어주어야함.
    //mybatis처리하기전 전처리/후처리 용도
    public List<BoardDTO> getList();
    public BoardDTO get(Long no);
    public BoardDTO create(BoardDTO board);
    public BoardDTO update(BoardDTO board);
    public BoardDTO delete(Long no);
}
