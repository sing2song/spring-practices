package com.saltlux.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saltlux.mysite.repository.BoardRepository;
import com.saltlux.mysite.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;

	public void write(BoardVo vo) {
		boardRepository.insert(vo);
	}

}
